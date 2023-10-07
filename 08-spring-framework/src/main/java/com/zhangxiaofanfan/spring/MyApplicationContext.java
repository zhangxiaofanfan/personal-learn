package com.zhangxiaofanfan.spring;

import com.zhangxiaofanfan.spring.annotation.*;
import com.zhangxiaofanfan.spring.exception.NoSuchBeanException;
import com.zhangxiaofanfan.spring.myinterface.ApplicationContext;
import com.zhangxiaofanfan.spring.myinterface.BeanNameAware;
import com.zhangxiaofanfan.spring.myinterface.BeanPostProcessor;
import com.zhangxiaofanfan.spring.myinterface.InitializingBean;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author 张晓帆帆
 * @version 1.0
 * @projectName 05-my-framework
 * @date 2021-08-28 下午4:18
 * @description ioc 容器的自定义实现
 */
public class MyApplicationContext implements ApplicationContext {

    /**
     * singletonObjects: 用来保存单实例 Bean 的 Map
     * beanDefinitionMap: 用来保存 Bean 定义信息的 Map
     */
    private ConcurrentHashMap<String, Object> singletonObjectMap = new ConcurrentHashMap<>();
    private ConcurrentHashMap<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();

    public MyApplicationContext(Class<?> configClass) {
        scanComponent(configClass);
        initSingletonInstance();
    }

    @Override
    public Object createBean(String beanName, BeanDefinition beanDefinition) {
        Class<?> clazz = beanDefinition.getClazz();
        try {
            Object instance = clazz.getConstructor().newInstance();
            // 依赖注入
            for (Field field : clazz.getFields()) {
                if (field.isAnnotationPresent(Autowired.class)) {
                    field.setAccessible(true);
                    Object bean = getBean(field.getName());
                    field.set(instance, bean);
                }
            }

            // BeanNameAware 功能回调
            if (instance instanceof BeanNameAware) {
                BeanNameAware bean = (BeanNameAware) instance;
                bean.setName(beanName);
            }

            // 初始化前 功能回调
            if (instance instanceof BeanPostProcessor) {
                BeanPostProcessor bean = (BeanPostProcessor) instance;
                bean.postProcessorBeforeInitialization(bean, beanName);
            }

            // 属性设置后 功能回调
            if (instance instanceof InitializingBean) {
                InitializingBean bean = (InitializingBean) instance;
                bean.afterPropertiesSet();
            }

            // 初始化后 功能回调
            if (instance instanceof BeanPostProcessor) {
                BeanPostProcessor bean = (BeanPostProcessor) instance;
                bean.postProcessorAfterInitialization(bean, beanName);
            }

            return instance;
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 使用绝对路径查找 ComponentScan 扫描包下的类文件,
     * TODO: 在第二版本可以使用相对路径对扫描 Component 组件
     * @param configClass 配置类
     */
    @Override
    public void scanComponent(Class<?> configClass) {
        final String CLASS_SUFFIX = ".class";
        final String BLANK_STRING = "";
        // 解析 ComponentScan 注解 --> 获取需要扫描的路径 --> 扫描路径内添加了 Component 注解的类
        ComponentScan componentScanAnnotation = configClass.getDeclaredAnnotation(ComponentScan.class);
        String path = componentScanAnnotation.value().replace('.', File.separatorChar);
        // 使用 app 类加载器, 可以加载 classpath 下的类
        ClassLoader applicationClassLoader = this.getClass().getClassLoader();
        URL resource = applicationClassLoader.getResource(path);
        assert resource != null;
        File file = new File(resource.getFile());
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            assert files != null;
            for (File f : files) {
                String classPath = f.getAbsolutePath();
                if (classPath.endsWith(CLASS_SUFFIX)) {
                    String className = classPath.substring(classPath.indexOf(path), classPath.indexOf(CLASS_SUFFIX))
                            .replace(File.separatorChar, '.');
                    try {
                        Class<?> clazz = applicationClassLoader.loadClass(className);
                        if (clazz.isAnnotationPresent(Component.class)) {
                            // 表示当前类需要被当做一个 Bean 对象来进行后续的处理
                            Component componentAnnotation = clazz.getDeclaredAnnotation(Component.class);
                            String beanName = componentAnnotation.value();
                            if (BLANK_STRING.equals(beanName)) {
                                beanName = transSmallHump(clazz.getName().substring(clazz.getName().lastIndexOf('.') + 1));
                            }
                            BeanDefinition beanDefinition = new BeanDefinition();
                            beanDefinition.setClazz(clazz);

                            // 现阶段 Scope 只存在两种方式
                            if (clazz.isAnnotationPresent(Scope.class)) {
                                beanDefinition.setScope(clazz.getDeclaredAnnotation(Scope.class).value());
                            } else {
                                beanDefinition.setScope(ScopePolicy.SINGLETON);
                            }
                            beanDefinitionMap.put(beanName, beanDefinition);
                        }

                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    /**
     * 从 ioc 容器中获取满足要求的实例Bean, 按照 BeanName 从容器中获取 Bean 对象;
     * @exception NoSuchBeanException 容器中不存在 BeanName 的对象, 主动排除异常;
     * TODO 第二版 Bean 对象获取规则:
     *  1. 按照 Class 类型获取, 如果该类型的对象在容器中只存在一个, 则将这个对象进行返回;
     *  2. 如果该类型的对象存在多个, 按照名称获取, 返回查找到第一个 Bean 对象;
     *  3. 按照类型查找不到, 或者查找到多个, 按照 BeanName 查找不到, 抛出异常并退出;
     */
    @Override
    public <T> T getBean(String beanName, Class<T> beanClass) {
        if (beanDefinitionMap.containsKey(beanName)) {
            BeanDefinition beanDefinition = beanDefinitionMap.get(beanName);
            if (beanDefinition.getScope() == ScopePolicy.SINGLETON)
                return beanClass.cast(singletonObjectMap.get(beanName));
            else
                return beanClass.cast(createBean(beanName, beanDefinition));
        } else {
            throw new NoSuchBeanException("不包含bean: " + beanName);
        }
    }

    @Override
    public Object getBean(String beanName) {
        return getBean(beanName, Object.class);
    }

    /**
     * 将 BeanName 转换成小驼峰
     * @param beanName 不为null, 并且 beanName.length() >= 1
     * @return beanName 小驼峰形式
     */
    private String transSmallHump(String beanName) {
        if (beanName == null || beanName.length() == 0) return beanName;
        char firstChar = beanName.charAt(0);
        if (firstChar >= 'A' && firstChar <= 'Z') {
            firstChar = (char)('a' + firstChar - 'A');
            beanName = firstChar + beanName.substring(1);
        }
        return beanName;
    }

    /**
     * 实例容器中组件作用域范围为 ScopePolicy.SINGLETON 的组件，并将组件存放在 singletonObjectMap 内
     */
    private void initSingletonInstance() {
        // 将所有单实例的Bean对象创建完成后存放在 singletonObjectMap 中
        for (String beanName : this.beanDefinitionMap.keySet()) {
            BeanDefinition beanDefinition = beanDefinitionMap.get(beanName);
            if (beanDefinition.getScope() == ScopePolicy.SINGLETON) {
                this.singletonObjectMap.put(beanName, createBean(beanName, beanDefinition));
            }
        }
    }
}

> 当前模块(springboot-learn)是学习《Spring Boot源码解读与原理分析》使用项目

## Chap1: Spring Boot整体概述



关系: Java --> Spring Framework --> SpringBoot

- Java: 作为一门开发语言，指定了使用该语言进行开发的基础标准；
- Spring Framework: 一个基于Java语言的、开源的、松耦合的、分层的、可配置的一站式企业级开发框架;
- SpringBoot: 基于 Spring Framework 之上的==二次封装==, 旨在简化基于Spring Framework 的项目搭建和应用开发;

学习使用环境:

- Java: jdk8;
- Spring Framework: 5.2.15.RELEASE
- SpringBoot: 2.3.11.RELEASE(默认引入 Spring 5.2.X)
- maven: 3.5.0

## Chap2: Spring Boot自动装配

### 1. 模块装配

案例场景: 

   使用代码构建一个==酒馆==, 酒馆里面有==吧台==、==调酒师==、==服务员==和==老板==4种不同的实体元素。在该场景中，就把可以看做是 ApplicationContext, 酒吧中的 吧台、调酒师、服务员和老板可以看做是==组件==。 使用代码模拟的最终目的是: ***可以通过一个注解, 把以上的角色填充到酒馆中***, 以实现组件装配过程;



### 5. Spring Boot装配机制

- SpringBoot 的自动装配实际上就是: 模块装配 + 条件状态 + SPI机制的组合

```java
// ...
// EnableAutoConfiguration 会触发自动装配功能
// ComponentScan 会触发组件扫描功能
@SpringBootConfiguration
@EnableAutoConfiguration
@ComponentScan(excludeFilters = { 
    @Filter(type = FilterType.CUSTOM, classes = TypeExcludeFilter.class),
	@Filter(type = FilterType.CUSTOM, classes = AutoConfigurationExcludeFilter.class) 
})
public @interface SpringBootApplication {
    // 属性忽略
}
```

### 6. MvbMvc场景下的自动装配原理

> WebMvc场景的自动装配环节: Servlet容器的装配 --> DispatcherServlet的装配 --> WebMvc核心组件的装配

#### 1. Servlet 容器的装配

```java
@Configuration(proxyBeanMethods = false)
@AutoConfigureOrder(Ordered.HIGHEST_PRECEDENCE)
@ConditionalOnClass(ServletRequest.class)
@ConditionalOnWebApplication(type = Type.SERVLET)
@EnableConfigurationProperties(ServerProperties.class)
@Import({ 
    ServletWebServerFactoryAutoConfiguration.BeanPostProcessorsRegistrar.class,
	ServletWebServerFactoryConfiguration.EmbeddedTomcat.class,
	ServletWebServerFactoryConfiguration.EmbeddedJetty.class,
	ServletWebServerFactoryConfiguration.EmbeddedUndertow.class 
})
public class ServletWebServerFactoryAutoConfiguration 
```




仿写 yudao-cloud 项目使用

- 00-personal-demo: 父项目, 仿写使用的顶层管理

- zxff-dependencies: 依赖项目, 后端整体的依赖都在此项目中做统一管控
- zxff-gateway: 网关项目, 后端项目统一入口
- zxff-framework: 封装了系统使用的功能:
  - zxff-common: 公共服务;
  - zxff-spring-boot-starter-monitor: 封装了和监控相关的功能, 通过starter可以将此功能引入到系统中;
- zxff-module-system: 项目系统功能相关实现相关依赖;
  - `zxff-module-system-api`: 暴露外部可用的接口, 以 RPC 和 http 的形式向外提供服务;
  - `zxff-module-system-biz`: 服务接口的实现模块;

![功能分层](../../temp_pic/ruoyi-vue-pro-biz.png)



### 遇到问题

#### 1. 使用BOM管理项目依赖

问题描述: 当zxff-dependencies项目依赖父项目`00-personal-demo` 时, 会产生循环依赖的报错

修复方案: 

- 父项目通过module管理子项目`zxff-dependencies`;
- 子项目作为独立的 maven 项目, 不对父项目进行依赖, `zxff-dependencies` 只管理项目使用

方案修复参考地址: https://blog.csdn.net/AlbenXie/article/details/121195708

#### 2. 项目中引入的依赖来源

问题描述: 原始项目中存在一个 `org.hibernate.validator.constraints.URL` 无法确定是通过哪个坐标来引入到项目中的;

解决方案:

- 使用命令: `mvn dependency:tree` 列出依赖树;
- 在依赖树中查找依赖, 并根据依赖书反查出坐标, 最后定位是 `xxxx-spring-boot-starter-web` 中通过 `spring-boot-starter-validation` 坐标引入到项目中;
- 步骤如下图所示;

![image-20240416175730122](../../temp_pic/image-20240416175730122.png)


# examOnline
该项目是基于Spring cloud微服务架构来实现的在线考试系统，包括API网关、注册中心、配置中心、授权中心以及后端的服务。主要的业务功能可以参考另一个开源项目examstack。在该项目中同时也会使用到一些第三方的开源项目，如：Spring boot admin来处理应用的监控；scca来实现图形化的应用配置中心；等，

1. exam-eureka：通过eureka实现的服务注册中心
2. exam-config-server：基于数据库的配置中心
3. exam-config-admin：基于数据库的配置中心的rest服务，提供UI管理接口
4. exam-admin：后端管理平台
5. exam-auth：授权中心
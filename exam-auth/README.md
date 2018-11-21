### exam-auth
授权中心

1. auth-server：授权中心服务端
	1. 添加依赖
	2. 编写配置文件
	3. 编写主类
2. auth-client：授权中心客户端
3. 注意事项
	1. Spring Boot 2.0 JPA生成的table默认采用的mysiam存储引擎，需要在配置文件中指定方言设置

			database-platform: org.hibernate.dialect.MySQL57InnoDBDialect
	2. eureka-client：需要添加spring-boot-starter-web
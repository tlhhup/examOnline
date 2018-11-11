### 配置中心搭建
在Spring cloud提供的配置中心具有两种方式来实现配置的存储，一种采用git，另一种采用db的方式。本系统主要采用db的方式储存配置信息，以便于管理。可以通过地址API的方式来对配置信息通过可视化界面进行管理，同时也可以使用第三方开源项目来实现，如：SCCA。这里先讲解采用Spring cloud提供的db来搭建配置中心，后面的章节中在讲解集成SCCA来实现配置信息的可视化管理。

1. 添加依赖

		<dependencies>
	        <dependency>
	            <groupId>org.springframework.cloud</groupId>
	            <artifactId>spring-cloud-config-server</artifactId>
	        </dependency>
	        <dependency>
	            <groupId>org.springframework.boot</groupId>
	            <artifactId>spring-boot-starter-jdbc</artifactId>
	        </dependency>
	        <dependency>
	            <groupId>mysql</groupId>
	            <artifactId>mysql-connector-java</artifactId>
	        </dependency>
	        <dependency>
	            <groupId>org.springframework.cloud</groupId>
	            <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
	        </dependency>
	    </dependencies>
2. 添加配置信息

		server:
		  port: 10013
		spring:
		  datasource:
		    url: jdbc:mysql:///exam_config
		    driver-class-name: org.gjt.mm.mysql.Driver
		    username: root
		    password: 123456
		    type: org.springframework.jdbc.datasource.DriverManagerDataSource
		  profiles:
		    active: jdbc # 激活config-DB方式，该配置是必须的
		  cloud:
		    config:
		      server:
		        jdbc:
		          sql: SELECT KEY, VALUE from PROPERTIES where APPLICATION=? and PROFILE=? and LABEL=?
		          order: 0
		  application:
		    name: exam-config-server
		eureka:
		  client:
		    service-url:
		      defaultZone: http://localhost:10011/eureka/
		    healthcheck:
		      enabled: true
		  instance:
		    appname: exam-config-server
		    lease-expiration-duration-in-seconds: 5
		    lease-renewal-interval-in-seconds: 5
3. 编写启动类

		@EnableEurekaClient
		@EnableConfigServer
		@SpringBootApplication
		public class ExamConfigServerApplication {
		
		    public static void main(String[] args) {
		        SpringApplication.run(ExamConfigServerApplication.class,args);
		    }
		
		}
4. 初始化数据库结构

		create database exam_config;
		use exam_config;
		create table PROPERTIES(
		 APPLICATION varchar(200),
		 `PROFILE` varchar(50),
		 LABEL varchar(50),
		 `KEY` varchar(100),
		 `VALUE` varchar(500)
		);
		 
		insert into PROPERTIES values('consume-feign','dev',‘master’,'name','name-dev');
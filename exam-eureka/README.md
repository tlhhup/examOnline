### 基于eureka搭建的注册中心
通过Spring cloud包装的Netflix的服务发现组建eureka来搭建项目中的注册中心，提供服务发现和软负载。

1. 环境搭建
	1. 添加依赖

			<dependencies>
		        <dependency>
		            <groupId>org.springframework.cloud</groupId>
		            <artifactId>spring-cloud-starter-netflix-eureka-server</artifactId>
		        </dependency>
		    </dependencies>
	
	2. 编写启动类 

			@EnableEurekaServer			// 开启eureka server支持
			@SpringBootApplication		// 标示为Spring boot项目
			public class ExamEurekaApplication {
			
			    public static void main(String[] args) {
			        SpringApplication.run(ExamEurekaApplication.class,args);
			    }
			
			}
2. 单机模式：及只存在一个注册中心，这样存在单节点问题
	1. 编写配置文件application.yml文件，添加一下内容：

			server:
			  port: 10010
			spring:
			  application:
			    name: exam-eureka
			eureka:
			  instance:
			    hostname: localhost
			    appname: exam-eureka
			  client:
			    registerWithEureka: false	# 不向注册中心注册
			    fetchRegistry: false	# 不拉去注册中心的服务信息
			    serviceUrl:
			      defaultZone: http://${eureka.instance.hostname}:${server.port}/eureka/
			  server:
			    enable-self-preservation: false # 开发阶段关闭保护模式
			    eviction-interval-timer-in-ms: 5000 # client 清理时间 
3. 高可用模式：及存在多个注册中心，并且**彼此之间相互注册**
	1. first注册中心

			server:
				  port: 10010
				spring:
				  application:
				    name: exam-eureka
				eureka:
				  instance:
				    hostname: localhost
				    appname: exam-eureka
				  client:
				    serviceUrl:
				      defaultZone: http://${eureka.instance.hostname}:10011/eureka/ # 将自己组册到第二个注册中心中
				  server:
				    enable-self-preservation: false # 开发阶段关闭保护模式
				    eviction-interval-timer-in-ms: 5000 # client 清理时间 
	2. second注册中心 

			server:
				  port: 10011
				spring:
				  application:
				    name: exam-eureka
				eureka:
				  instance:
				    hostname: localhost
				    appname: exam-eureka
				  client:
				    serviceUrl:
				      defaultZone: http://${eureka.instance.hostname}:10010/eureka/ # 将自己组册到第一个注册中心中
				  server:
				    enable-self-preservation: false # 开发阶段关闭保护模式
				    eviction-interval-timer-in-ms: 5000 # client 清理时间 
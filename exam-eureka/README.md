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
4. 通过profiles简化高可用配置：    
    1. 通过Spring提供的profiles来处理多环境配置(同一的注册中心，配置不同而已)

			# default配置
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
			    registerWithEureka: false
			    fetchRegistry: false
			    serviceUrl:
			      defaultZone: http://${eureka.instance.hostname}:${server.port}/eureka/
			  server:
			    enable-self-preservation: false # 开发阶段关闭保护模式
			    eviction-interval-timer-in-ms: 5000 # client 清理时间
			
			# master配置
			---
			spring:
			  profiles: master
			server:
			  port: 10010
			eureka:
			  client:
			    registerWithEureka: true
			    fetchRegistry: true
			    serviceUrl:
			      defaultZone: http://${eureka.instance.hostname}:10011/eureka/
			
			# slave配置
			---
			spring:
			  profiles: slave
			server:
			  port: 10011
			eureka:
			  client:
			    registerWithEureka: true
			    fetchRegistry: true
			    serviceUrl:
			      defaultZone: http://${eureka.instance.hostname}:10010/eureka/
  2. 说明
  		1. 激活多环境，通过启动参数`spring.profiles.active`设置环境

  				java -jar exam-eureka-1.0.0.jar --spring.profiles.active=slave
  		2. 配置说明：默认的配置会继续派生到每个环境中，可以在各个环境中覆盖原有的默认配置 
5. 通过docker-maven插件构建docker镜像
	1. 添加插件

			<build>
		        <plugins>
		            <plugin>
		                <groupId>com.spotify</groupId>
		                <artifactId>docker-maven-plugin</artifactId>
		                <version>${docker.plugin.version}</version>
		                <configuration>
		                    <!-- 设置image的名称 -->
		                    <imageName>${docker.prefix}/${project.artifactId}</imageName>
		                    <!-- 设置Dockerfile文件路径 -->
		                    <dockerDirectory>${project.basedir}/src/main/docker</dockerDirectory>
		                    <resources>
		                        <resource>
		                            <targetPath>/</targetPath>
		                            <directory>${project.build.directory}</directory>
		                            <include>${project.build.finalName}.jar</include>
		                        </resource>
		                    </resources>
		                </configuration>
		            </plugin>
		        </plugins>
		    </build>
	2. 编写Dockerfile文件：在src/main/docker文件夹下创建Dockerfile文件，其内容如下

			# 基础镜像
			FROM livingobjects/jre8
			
			# 卷
			VOLUME /tmp
			
			# copy file
			ADD exam-eureka-1.0.0.jar app.jar
			RUN bash -c 'touch /app.jar'
			
			# 容器在运行时监听的端口号，用于docker容器间的通信
			EXPOSE 10010
			
			ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
	3. 执行命令  

			mvn clean package docker:build
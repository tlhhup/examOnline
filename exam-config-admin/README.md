### 配置中心集中化管理
在上节中完成了基于数据库的配置中心的搭建，但是配置信息的修改和添加需要通过SQL语句来处理，过于繁琐，本节将集成SCCA开源项目来实现配置信息的UI管理。由于该项目目前适配到Spring boot的版本为1.5.X，古需要修改parent，并且只搭建rest服务，不提供UI管理。UI管理模块将在exam-admin模块中进行处理，并将会做适当修改来匹配当前项目需求。

1. 添加依赖

		<parent>
	        <groupId>org.springframework.boot</groupId>
	        <artifactId>spring-boot-starter-parent</artifactId>
	        <version>1.5.13.RELEASE</version>
	    </parent>
	    <modelVersion>4.0.0</modelVersion>
	
	    <artifactId>exam-config-admin</artifactId>
	    <properties>
	        <scca.version>1.0.0-RELEASE</scca.version>
	        <spring.boot.admin.version>1.5.7</spring.boot.admin.version>
	    </properties>
	    <dependencies>
	        <dependency>
	            <groupId>org.springframework.boot</groupId>
	            <artifactId>spring-boot-starter-web</artifactId>
	        </dependency>
	        <dependency>
	            <groupId>com.didispace</groupId>
	            <artifactId>scca-rest</artifactId>
	            <version>${scca.version}</version>
	        </dependency>
	        <dependency>
	            <groupId>com.didispace</groupId>
	            <artifactId>scca-persistence-db</artifactId>
	            <version>${scca.version}</version>
	        </dependency>
	        <dependency>
	            <groupId>com.didispace</groupId>
	            <artifactId>scca-discovery-eureka</artifactId>
	            <version>${scca.version}</version>
	        </dependency>
	        <dependency>
	            <groupId>mysql</groupId>
	            <artifactId>mysql-connector-java</artifactId>
	        </dependency>
	        <dependency>
	            <groupId>org.projectlombok</groupId>
	            <artifactId>lombok</artifactId>
	        </dependency>
	        <dependency>
	            <groupId>org.springframework.boot</groupId>
	            <artifactId>spring-boot-starter-actuator</artifactId>
	        </dependency>
	    </dependencies>
2. 编写配置文件
	1. application.yml

			server:
			 port: 10014
			scca:
			 rest:
			   context-path: /xhr
			spring:
			 datasource:
			   url: jdbc:mysql:///exam_config
			   driver-class-name: org.gjt.mm.mysql.Driver
			   username: root
			   password: 123456
			 jpa:
			   hibernate:
			     ddl-auto: update
			 application:
			   name: exam-config-admin
	2. bootstrap.yml(通过服务发现的方式来拉去配置中心的配置，healthcheck需要关闭)

			spring:
			  cloud:
			    config:
			      discovery:
			        enabled: true
			        service-id: exam-config-server
			eureka:
			 client:
			   service-url:
			     defaultZone: http://localhost:10011/eureka/
			 instance:
			   appname: exam-config-admin
			   lease-expiration-duration-in-seconds: 5
			   lease-renewal-interval-in-seconds: 5
3. 编写启动类

		@EnableEurekaClient
		@SpringBootApplication
		public class ExamConfigAdminApplication {
		
		    public static void main(String[] args) {
		        SpringApplication.run(ExamConfigAdminApplication.class,args);
		    }
		
		    @Bean
		    @LoadBalanced
		    public RestTemplate restTemplate(){
		        return new RestTemplate();
		    }
		
		}

4. 完成配置自动刷新接口

			@Slf4j
			@RestController
			@RequestMapping("/refresh")
			public class RefreshConfigController {
			
			    @Value("${spring.cloud.config.discovery.service-id}")
			    private String serviceId;
			
			    @Autowired
			    private RestTemplate restTemplate;
			
			    @PostMapping("/all")
			    public RefreshResDto refreshAll() {
			        RefreshResDto result=new RefreshResDto();
			        try {
			            StringBuilder builder = new StringBuilder("http://").append(serviceId).append("/actuator/bus-refresh");
			            HttpHeaders headers = new HttpHeaders();
			            //将content-type设置为null,不然config-server端会报415
			            headers.add("content-type", null);
			            HttpEntity<String> request = new HttpEntity<String>(null, headers);
			
			            this.restTemplate.postForEntity(builder.toString(), request, ResponseEntity.class);
			
			            result.setFlag(true);
			        } catch (RestClientException e) {
			            log.error("refresh all error");
			        }
			
			        return result;
			    }
			
			    @PostMapping("/{destination}")
			    public RefreshResDto refreshSpecific(@PathVariable("destination") String destination) {
			        RefreshResDto result=new RefreshResDto();
			        try {
			            StringBuilder builder = new StringBuilder("http://").append(serviceId).append("/actuator/bus-refresh/{destination}");
			            HttpHeaders headers = new HttpHeaders();
			            headers.add("content-type", null);
			            HttpEntity<String> request = new HttpEntity<String>(null, headers);
			            this.restTemplate.postForEntity(builder.toString(), request, ResponseEntity.class, destination);
			            result.setFlag(true);
			        } catch (RestClientException e) {
			            log.error("refresh {} error", destination);
			        }
			
			        return result;
			    }
		
		}
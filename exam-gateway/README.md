### 基于Spring cloud Gateway网关搭建
网关是微服务架构中的重要部分用于隔离后端服务和前端请求，是前后分离的入口，同时也是后端服务的保障。

在Spring cloud提供的微服务架构套件中提供了两种网关，一种是zuul，一种则是Spring自己的Gateway。本节主要通过gateway来搭建考试系统的网关。

1. 添加依赖
	
	gateway运行容器依赖于netty，所有引入的依赖中**不能**存在Springmvc的依赖。
	
		<dependencies>
	        <dependency>
	            <groupId>org.springframework.cloud</groupId>
	            <artifactId>spring-cloud-starter-gateway</artifactId>
	        </dependency>
	        <dependency>
	            <groupId>org.springframework.cloud</groupId>
	            <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
	        </dependency>
	    </dependencies>
2. 编写配置文件

		server:
		  port: 10010
		spring:
		  application:
		    name: exam-gateway
		  cloud:
		    gateway:
		      routes:
		        - id: auth
		          uri: lb://exam-auth-server # lb开头表示负载均衡
		          predicates:
		            - Path=/auth/**     # /auth开头的请求都路由到exam-auth-server中
		
		eureka:
		  instance:
		    appname: exam-gateway
		    lease-expiration-duration-in-seconds: 5
		    lease-renewal-interval-in-seconds: 5
		  client:
		    service-url:
		      defaultZone: http://localhost:10011/eureka/
3. 跨域处理

	在前后分离开发中，前端主要透过官网来访问后端服务，而网关也一般部署在一台或多台不同于前端的物理机中，这个时候就会出现跨域的问题。在gateway中提供了两种方式来处理改问题，一个基础yml配置的方式(方便、灵活)，一种是基于WebFilter代码的方式来处理。
	1. 配置文件处理

		通过GlobalCorsProperties配置属性来完成跨域的配置，处理一个URL地址和一个CorsConfiguration之间的映射。其逻辑处理在org.springframework.web.cors.reactive.DefaultCorsProcessor#handleInternal该方法中完成。
	
			spring:
			  cloud:
			    gateway:
			      globalcors:
			        cors-configurations:
			          '[/**]':
			            allowedOrigins: "http://localhost:9527" # 前端服务器域名地址
			            allowedHeaders: "*"
			            allowedMethods:
			              - GET
			              - DELETE
			              - POST
			              - PUT
			              - OPTIONS		# 该请求用于获取服务器支持的HTTP请求方式，为跨域请求的预检请求，其目的是为了判断实际发送的请求是否是安全的
	2. 配置类处理(过滤器) 

			@Configuration
			public class GatewayConfig {
			
			    private static final String ALL = "*";
			    private static final String MAX_AGE = "18000L";
			
			
			    @Bean
			    public WebFilter corsFilter() {
			        return (ServerWebExchange ctx, WebFilterChain chain) -> {
			            ServerHttpRequest request = ctx.getRequest();
			            if (!CorsUtils.isCorsRequest(request)) {
			                return chain.filter(ctx);
			            }
			            HttpHeaders requestHeaders = request.getHeaders();
			            ServerHttpResponse response = ctx.getResponse();
			            HttpMethod requestMethod = requestHeaders.getAccessControlRequestMethod();
			            HttpHeaders headers = response.getHeaders();
			            headers.add(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, requestHeaders.getOrigin());
			            headers.addAll(HttpHeaders.ACCESS_CONTROL_ALLOW_HEADERS, requestHeaders.getAccessControlRequestHeaders());
			            if (requestMethod != null) {
			                headers.add(HttpHeaders.ACCESS_CONTROL_ALLOW_METHODS, requestMethod.name());
			            }
			            headers.add(HttpHeaders.ACCESS_CONTROL_ALLOW_CREDENTIALS, "true");
			            headers.add(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, ALL);
			            headers.add(HttpHeaders.ACCESS_CONTROL_MAX_AGE, MAX_AGE);
			            if (request.getMethod() == HttpMethod.OPTIONS) {
			                response.setStatusCode(HttpStatus.OK);
			                return Mono.empty();
			            }
			            return chain.filter(ctx);
			        };
			    }
	
			}
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
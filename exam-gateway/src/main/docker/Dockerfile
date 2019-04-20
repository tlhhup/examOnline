# 基础镜像
FROM livingobjects/jre8

# 卷
VOLUME /tmp

# copy file
ADD exam-gateway-1.0.0.jar app.jar
RUN bash -c 'touch /app.jar'

# 记录服务可用的端口
EXPOSE 10010

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-Dspring.profiles.active=deploy","-jar","/app.jar"]
#基础镜像
FROM openjdk:21

WORKDIR /app
# 设定时区
ENV TZ=Asia/Shanghai
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone
# 拷贝jar包
ADD target/gateway-service.jar .

EXPOSE 8080
# 入口
#ENTRYPOINT ["java", "-Dspring.profiles.active=pro", "--add-opens=java.base/java.lang=ALL-UNNAMED", "-jar", "/app/gateway-service.jar"]
ENTRYPOINT ["java", "-jar", "/app/gateway-service.jar"]
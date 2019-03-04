FROM openjdk:8-jdk-alpine
MAINTAINER Mageshwaran <mageshwaran.k.s@cognizant.com>
ADD target/greenstarapp-gateway-service.jar greenstarapp-gateway-service.jar
ENTRYPOINT ["java", "-jar", "/greenstarapp-gateway-service.jar"]
EXPOSE 8765
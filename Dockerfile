FROM openjdk:17
EXPOSE 8080
ADD target/springboot-docker-pipeline.jar springboot-docker-pipeline.jar
ENTRYPOINT ["java","-jar","/springboot-docker-pipeline.jar"]
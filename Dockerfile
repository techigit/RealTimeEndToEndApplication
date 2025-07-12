FROM openjdk:17
EXPOSE 8080
ADD target/springboot-docker-pipeline.war springboot-docker-pipeline.war
ENTRYPOINT ["java","-war","/springboot-docker-pipeline.war"]
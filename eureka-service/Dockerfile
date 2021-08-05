FROM openjdk:8
COPY ./target/eureka-service-0.0.1-SNAPSHOT.jar eureka-service.jar
#ADD book-service/target/book-service-0.0.1-SNAPSHOT.jar book-service.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "eureka-service.jar"]
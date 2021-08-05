FROM openjdk:8
COPY ./target/user-service-0.0.1-SNAPSHOT.jar user-service.jar
#ADD book-service/target/book-service-0.0.1-SNAPSHOT.jar book-service.jar
EXPOSE 8083
ENTRYPOINT ["java", "-jar", "user-service.jar"]
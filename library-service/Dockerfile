FROM openjdk:8
COPY ./target/library-service-0.0.1-SNAPSHOT.jar library-service.jar
#ADD book-service/target/book-service-0.0.1-SNAPSHOT.jar book-service.jar
EXPOSE 8084
ENTRYPOINT ["java", "-jar", "library-service.jar"]
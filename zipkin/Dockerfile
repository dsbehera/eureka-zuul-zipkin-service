FROM openjdk:8
COPY ./target/zipkin-0.0.1-SNAPSHOT.jar zipkin-log-server.jar
#ADD book-service/target/book-service-0.0.1-SNAPSHOT.jar book-service.jar
EXPOSE 8085
ENTRYPOINT ["java", "-jar", "zipkin-log-server.jar"]
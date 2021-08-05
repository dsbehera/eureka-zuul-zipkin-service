FROM openjdk:8
COPY ./target/zuul-0.0.1-SNAPSHOT.jar zull-gateway-service.jar
#ADD book-service/target/book-service-0.0.1-SNAPSHOT.jar book-service.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "zull-gateway-service.jar"]
# Spring Boot Microservice
Single repo for whole microservice architecture. 
Eureka service. 
FeignClient. 
Zuul gateway. 
Sleuth. 
Zipkin server. 
Liquibase. 
Mysql Db. 
Swagger Api Doc. 
Hystrix.  


MS1 > Book-Service, 
MS2 > User-Service, 
MS3 > Library-Service interacts with MS1 and MS2 using FeignClient, 
MS4 > Eureka-Service - All service registered here, 
MS5 > Zipkin - Distributed tracing, 
MS6 > Zuul-Gateway - gateway service.

Note: Please update spring boot version as required.

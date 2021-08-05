# sample-microservice
Sample MicroService with all these:
Eureka service.
FeignClient.
Zuul gateway.
Zipkin server init.

MS1 : Book-Service
MS2 : User-Service
MS3 : Library-Service interacts with MS1 and MS2 using FeignClient
MS4 : Eureka-Service - All service registered here
MS5 : Zipkin - Distributed tracing
MS6 : Zuul-Gateway - gateway service

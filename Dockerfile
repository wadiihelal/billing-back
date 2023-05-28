FROM openjdk:8
EXPOSE 9090
ADD target/billing.jar billing.jar
ENTRYPOINT ["java","-jar","/billing.jar"]

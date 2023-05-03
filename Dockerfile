FROM openjdk:8
EXPOSE 1200
ADD target/billing.jar billing.jar
ENTRYPOINT ["java","-jar","/billing.jar"]
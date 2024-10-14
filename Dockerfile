FROM openjdk:17-slim
WORKDIR /Eshopping
COPY target/eshopping.jar /Eshopping/Eshopping.jar
ENTRYPOINT ["java","-jar","Eshopping.jar"]
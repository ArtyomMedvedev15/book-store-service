FROM openjdk:17-oracle
ADD target/book-store-service-1.0.war book-store-service-1.0.war
EXPOSE 8080
CMD ["java", "-jar", "book-store-service-1.0.war"]
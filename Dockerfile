FROM openjdk:17-alpine3.14

RUN apk update && apk upgrade

RUN mkdir /app
WORKDIR /app

COPY . /app
RUN chmod +x mvnw && \
    ./mvnw clean install -DskipTests

EXPOSE 8080
CMD ['./mvnw', 'spring-boot:run']

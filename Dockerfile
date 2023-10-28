FROM openjdk:17-alpine3.14

RUN apk update && apk upgrade
RUN apk --no-cache add maven

RUN mkdir /app
WORKDIR /app

COPY . /app
RUN mvn clean install -DskipTests

EXPOSE 8080
CMD ["mvn", "spring-boot:run", "-pl api"]

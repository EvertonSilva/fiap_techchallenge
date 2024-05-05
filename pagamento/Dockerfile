FROM openjdk:17-alpine3.14 as builder

RUN mkdir /app
WORKDIR /app

RUN apk update && apk upgrade
RUN apk --no-cache add maven
COPY . .
RUN mvn clean package -DskipTests


FROM openjdk:17-alpine3.14 as release
RUN mkdir /release
WORKDIR /release

ENV SPRING_PROFILE prod

RUN mkdir api application database
COPY --from=builder /app/api/target ./api/target
COPY --from=builder /app/application/target ./application/target
COPY --from=builder /app/database/target ./database/target

EXPOSE 8080
CMD ["/bin/sh", "-c", "java -Dspring.profiles.active=${SPRING_PROFILE} -jar api/target/api-*.jar"]
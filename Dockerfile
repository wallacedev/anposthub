FROM openjdk AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN ./gradlew build

FROM openjdk:18-jdk-alpine3.14
RUN mkdir /app
COPY --from=build /home/gradle/src/build/libs/*.jar /app/spring-boot-application.jar
EXPOSE 8284
ENTRYPOINT ["java", "-jar","/app/spring-boot-application.jar"]


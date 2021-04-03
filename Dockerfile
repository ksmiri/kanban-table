FROM openjdk:8-jdk-alpine/maven-openjdk8
COPY ../kanban
WORKDIR /kanban
RUN mvn clean install
EXPOSE 4070
ENTRYPOINT ["java","-jar","/kanban/target/kanban.jar"]
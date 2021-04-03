FROM openjdk:8-jdk-alpine/maven-openjdk8
COPY . /home/cloud_user/kanban
WORKDIR /home/cloud_user/kanban
RUN mvn clean install
EXPOSE 4070
ENTRYPOINT ["java","-jar","/home/cloud_user/kanban/target/kanban.jar"]
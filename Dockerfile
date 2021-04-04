FROM adoptopenjdk/maven-openjdk8
COPY . ./home/cloud_user/kanban
WORKDIR /home/cloud_user/kanban
RUN mvn clean install
EXPOSE 4070
RUN mv /home/cloud_user/kanban/target/kanban*.jar /home/cloud_user/kanban/kanban.jar
ENTRYPOINT ["java","-jar","/home/cloud_user/kanban/kanban.jar"]
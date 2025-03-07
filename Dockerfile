FROM openjdk:21

WORKDIR /app

COPY target/ToDoListUILab3-0.0.1-SNAPSHOT.jar todoui.jar

EXPOSE 3030

ENTRYPOINT ["java", "-jar", "/app/todoui.jar"]

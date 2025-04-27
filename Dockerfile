FROM openjdk:21

WORKDIR /app

COPY target/ToDoListUILab3-0.0.1-SNAPSHOT.jar todoui.jar

# Открываем порт приложения (3030) и порт для отладки (5005)
EXPOSE 3030
EXPOSE 5005

# Запускаем приложение с параметрами отладки
CMD ["java", "-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5005", "-jar", "todoui.jar"]


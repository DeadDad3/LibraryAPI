FROM openjdk:17-jdk-slim

LABEL authors="DeadD"

WORKDIR /app

# Копируем JAR-файл приложения в контейнер
COPY target/LibraryAPI-0.0.1-SNAPSHOT.jar app.jar

# Устанавливаем переменные окружения для базы данных
ENV SPRING_DATASOURCE_URL=jdbc:postgresql://db:5432/library_db
ENV SPRING_DATASOURCE_USERNAME=${POSTGRES_USER}
ENV SPRING_DATASOURCE_PASSWORD=${POSTGRES_PASSWORD}

# Устанавливаем точку входа
ENTRYPOINT ["java", "-jar", "app.jar"]
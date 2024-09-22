# TaskManagementSystemApplication
**Project stack: Spring Boot, Hibernate, Flyway, PostgreSQL, Spring Security, JWT, JUnit 5, Mockito, Jakarta Bean Validation, Docker**

## Инструкция по запуску
Из папки с проектом запустить:
`docker-compose build && docker-compose up`

С документацией можно ознакомится здесь: http://localhost:8080/swagger-ui/index.html

## Описание проекта
Cистема управления задачами (Task Management System). Оеспечивает создание, редактирование, удаление и просмотр задач. Каждая задача содержит заголовок, описание, статус ("в ожидании", "в процессе", "завершено") и приоритет ("высокий", "средний", "низкий"), а также автора задачи и исполнителя. Пользователи могут просматривать задачи других пользователей, а исполнители задачи могут менять статус своих задач. К задачам можно оставлять комментарии.
API позволяет получать задачи конкретного автора или исполнителя, а также все комментарии к ним. 

# Техническая часть
В проекте используется Spring Boot, работа с базами данных реализована через Flyway, Hibernate, PostgreSQL. Авторизация через access JWT и использование refresh JWT для обновления (хранится в Cookie). Проект тестируется через JUnit 5 и Mockito. Используется валидация сущностей через Jakarta Bean Validation.


<img width="737" alt="Screenshot 2024-09-22 at 20 57 07" src="https://github.com/user-attachments/assets/2976ebeb-6891-4fe8-9081-003638c42daa">

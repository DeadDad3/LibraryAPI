# Library API

## Описание

Этот проект представляет собой API для управления библиотекой, разработанный с использованием **Spring Boot**. API позволяет выполнять основные операции, связанные с книгами, авторами, читателями и транзакциями библиотеки, а также включает базовую систему аутентификации для сотрудников.

## Основные сущности

- **Книга** (`Book`):
  - Поля: `id`, `название`, `год издания`, `авторы`.
- **Автор** (`Author`):
  - Поля: `id`, `имя`, `фамилия`, `дата рождения`, `книги`.
- **Читатель** (`Reader`):
  - Поля: `номер телефона`, `имя`, `фамилия`, `пол`, `дата рождения`.
- **Транзакция** (`Transaction`):
  - Поля: `тип операции (взятие/возврат)`, `дата и время операции`, `клиент`, `книга`.
- **Сотрудник** (`Employee`) *(опционально)*:
  - Поля: `id`, `логин`, `пароль`.

## API Методы

1. **Осуществление транзакции с книгой**  
   - Описание: Позволяет выполнить операцию по взятию или возврату книги, передавая ID клиента и книги.

2. **Поиск самого популярного автора**  
   - Описание: Возвращает автора, чьи книги были наиболее востребованы в указанном диапазоне дат (от...до).

3. **Поиск самого читающего клиента**  
   - Описание: Возвращает клиента, который взял наибольшее количество книг за всё время.

4. **Список всех читателей по количеству не сданных книг**  
   - Описание: Возвращает список всех читателей, отсортированный по количеству книг, которые они не вернули.

## Авторизация

Для выполнения методов **транзакции с книгой**, **поиска самого популярного автора** и **поиска самого читающего клиента** требуется аутентификация сотрудника. Нераспространённые методы защищены с использованием **JWT токенов**:

- **Метод логина**: Генерирует `access token` (срок действия — 10 минут) и `refresh token` (срок действия — 30 минут).
- **Метод обновления токена**: Позволяет получить новый `access token` по `refresh token`.

Метод для получения списка читателей по количеству не сданных книг является общедоступным и не требует авторизации.

## Технологии

- **Spring Boot** 3.0
- **Spring Security** — для аутентификации и защиты методов
- **JWT** — для генерации и обновления токенов доступа
- **Swagger** — для документирования и тестирования API
- **PostgreSQL** — в качестве базы данных
- **Docker** — для контейнеризации приложения

## Структура проекта

Проект включает следующие основные модули:

- **Контроллеры** (`Controller`): Определяют API конечные точки.
- **Сервисы** (`Service`): Содержат бизнес-логику приложения.
- **Репозитории** (`Repository`): Обеспечивают взаимодействие с базой данных.
- **Сущности** (`Entity`): Определяют структуру данных и соответствуют таблицам в БД.
- **Конфигурация безопасности** (`SecurityConfig`): Настраивает аутентификацию и авторизацию через Spring Security и JWT.

## Использование

### Запуск в Docker

Проект полностью готов к запуску в Docker. Для этого используются `Dockerfile` и `docker-compose.yml`, которые находятся в корне проекта.

1. Убедитесь, что у вас установлены **Docker** и **docker-compose**.
2. Выполните команду для запуска контейнеров:
   ```bash
   docker-compose up --build
   ```
### Тестовые данные

Для заполнения базы данных тестовыми значениями используется файл `data.sql`, который выполняется автоматически при запуске контейнера, чтобы можно было легко заполнить базу данных. В проекте также присутствует защита от Spring Security, поэтому многие запросы нужно делать будучи авторизованным пользователем, иначе доступ будет запрещён. Для тестирования использовались swagger и postman, все тесты прошли проверку.

---

### Настройка переменных окружения

Создайте файл `.env` в корне проекта, который будет содержать настройки для подключения к базе данных, такие как имя пользователя и пароль.

---

### Лицензия

Проект распространяется под [MIT License](LICENSE).

---

### Контакты

**Разработчик**: [DeadDad3](https://github.com/DeadDad3)  
**Репозиторий**: [LibraryAPI](https://github.com/DeadDad3/LibraryAPI)

[![Java CI with Gradle](https://github.com/BeyondDeGrave88/project2/actions/workflows/gradle.yml/badge.svg)](https://github.com/BeyondDeGrave88/project2/actions/workflows/gradle.yml)


# Проект: Автоматизация тестирования сервиса покупки туров

## Описание проекта
Проект содержит автоматизированные тесты для веб-сервиса покупки туров с использованием различных способов оплаты.

## Технологии
- Java 11
- JUnit 5
- Selenium WebDriver
- Allure Reports
- Docker
- MySQL

## Запуск проекта

### 1. Запуск контейнеров
```bash
# Запуск MySQL
docker-compose up -d

# Проверка работы контейнеров
docker-compose ps
```
### 2. Запуск тестируемого сервиса
```bash
# Запуск SUT (System Under Test)
java -jar ./artifacts/aqa-shop.jar

# Или с указанием профиля
java -jar -Dspring.profiles.active=test ./artifacts/aqa-shop.jar
```
### 3. Запуск тестов
```bash
# Запуск всех тестов
./gradlew test

# Запуск с генерацией Allure отчета
./gradlew test allureServe

# Запуск конкретного тестового класса
./gradlew test --tests "PayByCardPageTest"
```
### 4. Просмотр отчетов
```bash
# Генерация и открытие Allure отчета
./gradlew allureServe

# Сборка отчета в статические файлы
./gradlew allureReport
```
### Документация
- [План тестирования](https://github.com/BeyondDeGrave88/project2/blob/main/docs/Plan.md)

- [Отчет о тестировании](https://github.com/BeyondDeGrave88/project2/blob/main/docs/report.md)

- [Баг-репорты](https://github.com/BeyondDeGrave88/project2/blob/main/docs/bugs.md)

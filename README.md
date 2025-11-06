[![Java CI with Gradle](https://github.com/BeyondDeGrave88/project2/actions/workflows/gradle.yml/badge.svg)](https://github.com/BeyondDeGrave88/project2/actions/workflows/gradle.yml)


# Проект: Автоматизация тестирования сервиса покупки туров

## Описание проекта
Проект содержит автоматизированные тесты для веб-сервиса покупки туров с использованием различных способов оплаты.

## Начало работы
```bash
Небольшой набор инструкций, объясняющий, как получить копию этого проекта для запуска на локальном ПК.
```
## Prerequisites

Для использования проекта необходимо установить на ПК:

- **Git** - система контроля версий
```bash
# Windows (через Chocolatey)
choco install git

# macOS (через Homebrew)
brew install git

# Linux (Ubuntu/Debian)
sudo apt-get install git
```
- **Java 11**
```bash
# macOS
brew install openjdk@11

# Windows
# Скачать с Oracle.com или использовать Chocolatey
choco install openjdk11

# Linux (Ubuntu/Debian)
sudo apt-get install openjdk-11-jdk
```

- **Docker**
```bash
# macOS
brew install --cask docker

# Windows
# Скачать Docker Desktop с официального сайта

# Linux (Ubuntu/Debian)
sudo apt-get install docker.io docker-compose
```

- Браузер **Google Chrome** по [ссылке](https://www.google.com/intl/ru/chrome/)

- **IntelliJ IDEA** по [ссылке](https://www.jetbrains.com/idea/download/)

## Клонирование проекта
```bash
git clone https://github.com/BeyondDeGrave88/project2
```
## Переход в директорию проекта
```bash
cd project2
```
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

- [Отчет о тестировании](https://github.com/BeyondDeGrave88/project2/blob/main/docs/Report.md)

- [Отчет о проведенной автоматизации](https://github.com/BeyondDeGrave88/project2/blob/main/docs/Summary.md)

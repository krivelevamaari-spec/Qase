# Проект автоматизации тестирования сервиса [QASE](https://app.qase.io/)
![Логотип Qase](images/case_logo.png)
> Qase — это современная платформа управления тестированием для ручного и автоматизированного тестирования качества,\
> отслеживания и составления отчетов, которая помогает быстрее создавать программное обеспечение более высокого качества.

 # <a name="Описание">Описание</a>
 Проект тестирования включает в себя тесты пользовательского интерфейса и API. \
 Краткий список интересных фактов о проекте:
- [x] Архитектура автотестов базируется на паттерне Fluent Page Object, обеспечивающем последовательное выполнение шагов через цепочку вызовов (method chaining)
- [x] Генерация фиктивных данных с помощью библиотеки `Faker`
- [x] Конфигурация с использованием библиотеки `Owner`
- [x] Использование `Lombok` для моделей в тестах API   
- [x] Параметризованные тесты
- [x] Параметризованная сборка
- [x] Для запуска тестов используются разные конфигурационные файлы в зависимости от параметров сборки
- [x] Сериализация/десериализация объектов для запросов/ответов API с использованием `Jackson`
- [x] Использование спецификаций запроса/ответа для тестирования API
- [x] Пользовательский Allure listener для красивого логирования запросов/ответов API
- [x] Автоматические тесты как тестовая документация

# <a name="Технология">Инструменты и технологии</a>
<p  align="center">
  <code><img width="5%" title="IntelliJ IDEA" src="./images/icons/IDEA-logo.svg"></code>
   <code><img width="5%" title="Java" src="./images/icons/java-logo.svg"></code>
  <code><img width="5%" title="Selenide" src="./images/icons/selenide-logo.svg"></code>
  <code><img width="5%" title="REST-Assured" src="./images/icons/rest-assured-logo.svg"></code>
  <code><img width="5%" title="Gradle" src="./images/icons/gradle-logo.svg"></code>
  <code><img width="5%" title="JUnit5" src="./images/icons/junit5-logo.svg"></code>
  <code><img width="5%" title="Allure Report" src="./images/icons/allure-Report-logo.svg"></code>
  <code><img width="5%" title="Allure TestOps" src="./images/icons/allure-ee-logo.svg"></code>
  <code><img width="5%" title="Github" src="./images/icons/git-logo.svg"></code>
  <code><img width="5%" title="Jenkins" src="./images/icons/jenkins-logo.svg"></code>
  <code><img width="5%" title="Telegram" src="./images/icons/Telegram.svg"></code>
</p>

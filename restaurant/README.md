# Домашнее задание по КПО "Ресторан"
### Работа выполнена Виноградовым Владимиром Андреевичем БПИ228
Проект написан на языке Kotlin и представляет собой консольное приложение с хранением данных в формате json

### Работа приложения
* Чтобы запустить приложение - клонируем репозиторий / скачиваем архив и запускаем __RestaurantApplication__
* Для корректного чтения файлов надо копировать полный путь файла, чтобы не получить ошибки
* Файлы находятся в папке data

### Данные пользователей
* Существует админ: логин - admin0, пароль - 1234
* Существует покупатель: логин - vova, пароль - 1111
* Есть возможность добавить своего покупателя / администратора. Чтобы добавить администратора, требуется ввести ключ __1234__

### Функции приложения
* Создание пользователей
* Вход
* Создание заказа, изменение, оплата, пополнение счета (для покупателя)
* Добавление новых предметов, изменение имеющихся, просмотр статистики (для админа)

### Паттерны в проекте
* Наблюдатель
* Абстрактная фабрика
* Цепочка обязанностей

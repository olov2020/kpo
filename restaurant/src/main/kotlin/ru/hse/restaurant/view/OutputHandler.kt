package ru.hse.restaurant.view

import ru.hse.restaurant.RestaurantInfo
import ru.hse.restaurant.model.Order
import ru.hse.restaurant.model.Statistics

interface OutputHandler {
    fun showAdminCommands()
    fun showCustomerCommands()
    fun showAuthCommands()
    fun showMessage(message: String)
    fun showError(message: String)
    fun showMenu(info: RestaurantInfo)
    fun showMenuAllBase(info: RestaurantInfo)
    fun showOrder(order: Order)
    fun showOrderStatus(order: Order)

    fun showOrderCommands()
    fun showStatistics(statistics: Statistics)
}

class ConsoleOutputHandler : OutputHandler {
    override fun showAdminCommands() {
        println("Список команд для администратора:")
        println()

        val maxWidth = 15 // Максимальная ширина ключа

        println("%-${maxWidth}s %s".format("addItem.", "Добавить новую позицию в меню ресторана"))
        println("%-${maxWidth}s %s".format("deleteItem.", "Удалить позицию из меню"))
        println("%-${maxWidth}s %s".format("changeQuantity.", "Изменить доступное количество у позиции в меню"))
        println("%-${maxWidth}s %s".format("seeStatistics.", "Посмотреть статистику ресторана"))
        println("%-${maxWidth}s %s".format("logout.", "Выйти из системы"))
    }

    override fun showCustomerCommands() {
        println("Список команд для посетителя:")
        println()

        val maxWidth = 13 // Максимальная ширина ключа

        println("%-${maxWidth}s %s".format("createOrder.", "Создать новый заказ"))
        println("%-${maxWidth}s %s".format("payFor.", "Заплатить за готовые заказы"))
        println("%-${maxWidth}s %s".format("addMoney.", "Пополнить счет"))
        println("%-${maxWidth}s %s".format("logout.", "Выйти из системы"))
    }

    override fun showAuthCommands() {
        val maxWidth = 10 // Максимальная ширина ключа
        println()
        println("Для использования приложения необходим вход в систему!")
        println()

        println("%-${maxWidth}s %s".format("register.", "Зарегистрироваться"))
        println("%-${maxWidth}s %s".format("login.", "Войти"))
    }

    override fun showMessage(message: String) {
        println(message)
    }

    override fun showError(message: String) {
        println("Ошибка: $message")
    }

    override fun showMenu(info: RestaurantInfo) {
        println("Текущее меню:")
        println(info.restaurant.getMenu())
    }

    override fun showMenuAllBase(info: RestaurantInfo) {
        println("Все позиции ресторана:")
        println(info.restaurant.getMenu().showAllMenu())
        println("Выручка ресторана: ${info.restaurant.getAmountOfRevenue()}")
    }

    override fun showOrder(order: Order) {
        println(order)
    }

    override fun showOrderStatus(order: Order) {
        println(order.showOrderStatus())
    }

    override fun showOrderCommands() {
        println("Сформируйте ваш заказ:")
        println("1. Добавить новую позицию")
        println("2. Завершить выбор")
        println("3. Просмотреть заказ")
        println("4. Отменить заказ")
    }

    override fun showStatistics(statistics: Statistics) {
        println("Статистика ресторана:")
        println()

        val averageRating = statistics.getRestaurantStars()
        println("Рейтинг ресторана: ${"%.2f".format(averageRating)}")
        println("")

        println("Средние оценки для блюд:")
        statistics.getAverageStars().forEach { (item, stars) ->
            println("${item.getName()}: ${"%.2f".format(stars)}")
        }
        println("")

        println("Популярность блюд по количеству заказов:")
        statistics.getPopularItemsByCount().forEach { (item, count) ->
            println("${item.getName()}: $count")
        }
        println("")
    }
}

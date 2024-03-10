package ru.hse.restaurant.controller.customer

import ru.hse.restaurant.RestaurantInfo
import ru.hse.restaurant.controller.RestaurantProcess
import ru.hse.restaurant.model.*
import ru.hse.restaurant.view.ConsoleOutputHandler
import ru.hse.restaurant.reader.ConsoleUserReader

class PayForOrderCommand(
    private val reader: ConsoleUserReader = ConsoleUserReader(),
    private val outputHandler: ConsoleOutputHandler = ConsoleOutputHandler(),
) : RestaurantProcess {
    override fun process(restaurantInfo: RestaurantInfo) {
        val customer = restaurantInfo.currSession.getUser() as? Customer
        when {
            customer == null -> outputHandler.showError("Не удалось определить пользователя.")
            getCurrentOrders(
                customer,
                restaurantInfo
            ).isEmpty() -> outputHandler.showMessage("У вас нет готовых заказов для оплаты.")

            else -> {
                val customerOrders = getCurrentOrders(customer, restaurantInfo)
                outputHandler.showMessage("Ваши активные заказы:")
                customerOrders.forEachIndexed { index, order ->
                    outputHandler.showMessage("${index + 1}. $order")
                }
                val totalCost = customerOrders.sumOf { it.getTotalCost() }
                outputHandler.showMessage("Баланс вашего счета: ${customer.getBalance()}")
                outputHandler.showMessage("Общая стоимость ваших заказов: $totalCost")
                if (payForOrders(customerOrders, customer)) {
                    outputHandler.showMessage("Обновленный баланс вашего счета: ${customer.getBalance()}")
                    restaurantInfo.restaurant.setAmountOfRevenue(restaurantInfo.restaurant.getAmountOfRevenue() + totalCost)
                    outputHandler.showMessage("Заказ(ы) успешно оплачены")
                    rateOrders(customerOrders, restaurantInfo)
                    removeOrdersFromRestaurant(customerOrders, restaurantInfo)
                } else {
                    outputHandler.showMessage("На счете недостаточно средств для оплаты")
                }
            }
        }
    }

    private fun getCurrentOrders(customer: Customer, info: RestaurantInfo): List<Order> {
        return info.restaurant.getOrders().filter { it.getCustomer() == customer && it.getStatus() == OrderStatus.READY}
    }

    private fun payForOrders(orders: List<Order>, customer: Customer): Boolean {
        val totalCost = orders.sumOf { it.getTotalCost() }
        return customer.payOrder(totalCost)
    }

    private fun removeOrdersFromRestaurant(orders: List<Order>, restaurantInfo: RestaurantInfo) {
        val restaurant = restaurantInfo.restaurant
        orders.forEach { order ->
            restaurant.getOrders().remove(order)
        }
    }

    private fun rateOrders(orders: List<Order>, restaurantInfo: RestaurantInfo) {
        outputHandler.showMessage("Оцените свои заказы!")
        orders.forEach { order ->
            outputHandler.showOrder(order)
            outputHandler.showMessage("Введите вашу оценку от 1 до 5:")
            val rating = reader.readInt()
            outputHandler.showMessage("Введите ваш комментарий (необязательно):")
            val description = reader.readString()
            if (rating != null && rating in 1..5 && description != null) {
                restaurantInfo.restaurant.getStatistics().addFeedback(rating, description, order)
                outputHandler.showMessage("Спасибо за вашу оценку!")
            } else {
                outputHandler.showError("Некорректные данные")
            }
        }
    }
}

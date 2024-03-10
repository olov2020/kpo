package ru.hse.restaurant.controller.customer

import ru.hse.restaurant.RestaurantInfo
import ru.hse.restaurant.controller.RestaurantProcess
import ru.hse.restaurant.model.*
import ru.hse.restaurant.view.ConsoleOutputHandler
import ru.hse.restaurant.reader.ConsoleUserReader

class CreateOrderCommand(
    private val reader: ConsoleUserReader = ConsoleUserReader(),
    private val outputHandler: ConsoleOutputHandler = ConsoleOutputHandler(),
) : RestaurantProcess {
    override fun process(restaurantInfo: RestaurantInfo) {
        val order = Order(customer = (restaurantInfo.currSession.getUser() as? Customer) ?: return)

        val currentMenu = restaurantInfo.restaurant.getMenu()

        while (true) {
            outputHandler.showOrderCommands()

            when (reader.readInt()) {
                1 -> addItemToOrder(order, restaurantInfo, currentMenu)
                2 -> if (finishOrder(order, restaurantInfo)) {
                    break
                }

                3 -> outputHandler.showOrder(order)
                4 -> {
                    cancelOrder(order, restaurantInfo)
                    break
                }

                else -> outputHandler.showMessage("Некорректный выбор.")
            }
        }
    }

    private fun addItemToOrder(order: Order, restaurantInfo: RestaurantInfo, currentMenu: Menu) {
        outputHandler.showMenu(restaurantInfo)
        outputHandler.showMessage("Введите название позиции:")
        val inputItem = reader.readString()

        inputItem?.let { itemName ->
            val currItem = currentMenu.getItemByName(itemName)
            if (currItem != null && currItem.getCount() != 0.toLong()) {
                order.addItem(currItem)
                currItem.setCount(currItem.getCount()?.minus(1))
            } else {
                outputHandler.showError("Позиция не найдена в меню")
            }
        } ?: outputHandler.showError("Некорректное название позиции")
    }

    private fun finishOrder(order: Order, restaurantInfo: RestaurantInfo): Boolean {
        if (order.getItems().isEmpty()) {
            outputHandler.showMessage("Ваш заказ пуст. Добавьте позиции перед завершением.")
            return false
        } else {
            outputHandler.showOrder(order)
            outputHandler.showMessage("Подтверждаете ли вы свой заказ? (y/n):")
            val confirmation = reader.readString()
            return if (confirmation.equals("y", ignoreCase = true)) {
                restaurantInfo.restaurant.getOrders()?.add(order)
                outputHandler.showMessage("Ваш заказ принят и отправлен на обработку.")
                val customer = restaurantInfo.currSession.getUser() as Customer
                order.registerObserver(customer)
                order.setStatus(OrderStatus.ACCEPTED)
                restaurantInfo.restaurant.getOrderManager().processOrder(order)
                true
            } else {
                cancelOrder(order, restaurantInfo)
                true
            }
        }
    }

    private fun cancelOrder(order: Order, restaurantInfo: RestaurantInfo) {
        val restaurant = restaurantInfo.restaurant
        val menu = restaurant.getMenu()

        order.getItems().forEach { item ->
            val currItem = item.getName()?.let { menu.getItemByName(it) }
            if (currItem != null) {
                currItem.setCount(item.getCount()?.let { currItem.getCount()?.plus(it) })
            } else {
                menu.addItem(item)
            }
        }

        restaurant.getOrders().remove(order)
        outputHandler.showMessage("Ваш заказ отменен")
    }
}

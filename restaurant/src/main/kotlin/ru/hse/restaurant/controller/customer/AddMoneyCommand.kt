package ru.hse.restaurant.controller.customer

import ru.hse.restaurant.RestaurantInfo
import ru.hse.restaurant.controller.RestaurantProcess
import ru.hse.restaurant.model.Customer
import ru.hse.restaurant.view.ConsoleOutputHandler
import ru.hse.restaurant.reader.ConsoleUserReader

class AddMoneyCommand(
    private val reader: ConsoleUserReader = ConsoleUserReader(),
    private val outputHandler: ConsoleOutputHandler = ConsoleOutputHandler(),
) : RestaurantProcess {
    override fun process(restaurantInfo: RestaurantInfo) {
        val customer = restaurantInfo.currSession.getUser() as? Customer
        if (customer != null) {
            outputHandler.showMessage("Ваш текущий баланс: ${customer.getBalance()}")
            outputHandler.showMessage("Введите сумму для пополнения:")
            val addToBalance = reader.readInt()?.toLong()
            if (addToBalance != null && addToBalance > 0) {
                customer.setBalance(customer.getBalance() + addToBalance)
                outputHandler.showMessage("Баланс успешно пополнен. Новый баланс: ${customer.getBalance()}")
            } else {
                outputHandler.showError("Некорректная сумма пополнения.")
            }
        } else {
            outputHandler.showError("Не удалось определить пользователя.")
        }
    }
}

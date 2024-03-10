package ru.hse.restaurant.controller

import ru.hse.restaurant.RestaurantInfo
import ru.hse.restaurant.view.ConsoleOutputHandler

class LogoutCommand(
    private val outputHandler: ConsoleOutputHandler = ConsoleOutputHandler(),
) : RestaurantProcess {
    override fun process(restaurantInfo: RestaurantInfo) {
        execute(restaurantInfo)
    }

    private fun execute(info: RestaurantInfo) {
        if (info.currSession.checkLogin()) {
            info.currSession.logout()
            outputHandler.showMessage("Выход из системы выполнен успешно.")
        } else {
            outputHandler.showMessage("Вы не в системе.")
        }
    }
}
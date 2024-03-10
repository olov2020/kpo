package ru.hse.restaurant.service

import ru.hse.restaurant.RestaurantInfo
import ru.hse.restaurant.controller.validation.CommandValidationHandler
import ru.hse.restaurant.controller.validation.ExecutionCommandHandler
import ru.hse.restaurant.controller.validation.LoginHandler
import ru.hse.restaurant.view.ConsoleOutputHandler

class AppManager {
    companion object {
        private val info: RestaurantInfo = RestaurantInfo()
        private val isFilledInfo = info.isFilledInfo()
        private var exit = false

        private val outputHandler: ConsoleOutputHandler = ConsoleOutputHandler()
        private var commandHandlers = LoginHandler(CommandValidationHandler(ExecutionCommandHandler()))

        @JvmStatic
        fun run() {
            if (!isFilledInfo) {
                outputHandler.showError("Проблема с доступом к базе данных ресторана")
                info.restaurant.getOrderManager().stopAllChefs()
                exit = true
            }
            if (!exit) {
                outputHandler.showMessage("Добро пожаловать в приложение Ресторан!")
            }
            while (!exit) {
                if (!info.currSession.checkLogin()) {
                    outputHandler.showAuthCommands()
                } else if (info.currSession.checkAdmin()) {
                    outputHandler.showAdminCommands()
                } else {
                    outputHandler.showCustomerCommands()
                }
                outputHandler.showMessage("Введите команду (или 'exit' для выхода): ")
                val command = readln()

                if (!commandHandlers.handle(command, info)) {
                    exit = true
                }
            }
        }
    }
}
package ru.hse.restaurant.controller.validation

import ru.hse.restaurant.RestaurantInfo
import ru.hse.restaurant.view.ConsoleOutputHandler

class CommandValidationHandler(private val nextHandler: CommandHandler?) : BaseCommandHandler(nextHandler) {
    override fun handle(command: String, info: RestaurantInfo): Boolean {
        if (command == "exit" || command == "register" || command == "login" || command == "logout") {
            return super.handle(command, info)
        }

        if (!info.commands.containsKey(command)) {
            ConsoleOutputHandler().showMessage("Неопознанная команда")
            return true
        } else if (info.currSession.checkAdmin() && (command == "addItem" || command == "deleteItem" || command == "changeQuantity" || command == "seeStatistics")) {
            return super.handle(command, info)
        } else if (!info.currSession.checkAdmin() && !(command == "addItem" || command == "deleteItem" || command == "changeQuantity" || command == "seeStatistics")) {
            return super.handle(command, info)
        } else {
            ConsoleOutputHandler().showMessage("У вас нет доступа к введенной команде")
            return true
        }
    }
}
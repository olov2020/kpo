package ru.hse.restaurant.controller.validation

import ru.hse.restaurant.RestaurantInfo

class LoginHandler(nextHandler: CommandHandler?) : BaseCommandHandler(nextHandler) {
    override fun handle(command: String, info: RestaurantInfo): Boolean {
        if (!info.currSession.checkLogin() && command != "register" && command != "login" && command != "exit") {
            return true
        }
        return super.handle(command, info)
    }
}
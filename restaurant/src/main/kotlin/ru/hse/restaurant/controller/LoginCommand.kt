package ru.hse.restaurant.controller

import ru.hse.restaurant.RestaurantInfo
import ru.hse.restaurant.autherization.UserData
import ru.hse.restaurant.reader.ConsoleUserReader
import ru.hse.restaurant.view.ConsoleOutputHandler

class LoginCommand(
    private val userData: UserData = UserData(),
    private val reader: ConsoleUserReader = ConsoleUserReader(),
    private val outputHandler: ConsoleOutputHandler = ConsoleOutputHandler(),
) : RestaurantProcess {
    override fun process(restaurantInfo: RestaurantInfo) {
        execute(restaurantInfo)
    }

    private fun execute(info: RestaurantInfo) {
        if (info.currSession.checkLogin()) {
            outputHandler.showMessage("Вход в систему уже выполнен")
        } else {
            outputHandler.showMessage("Введите логин:")
            val login = reader.readAuthData()
            outputHandler.showMessage("Введите пароль:")
            val password = reader.readAuthData()

            val admin =
                userData.admins.find { it.getLogin() == login && userData.validateAdminPassword(login, password) }
            val visitor = userData.customers.find {
                it.getLogin() == login && userData.validateCustomerPassword(
                    login, password
                )
            }

            if (admin != null) {
                info.currSession.login(admin)
                outputHandler.showMessage("Вход админа в систему выполнен успешно.")
            } else if (visitor != null) {
                info.currSession.login(visitor)
                outputHandler.showMessage("Вход посетителя в систему выполнен успешно.")
            } else {
                outputHandler.showMessage("Неверный логин или пароль.")
            }
        }
    }
}
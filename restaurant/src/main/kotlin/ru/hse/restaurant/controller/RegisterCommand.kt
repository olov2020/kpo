package ru.hse.restaurant.controller

import ru.hse.restaurant.RestaurantInfo
import ru.hse.restaurant.autherization.UserData
import ru.hse.restaurant.model.Administrator
import ru.hse.restaurant.model.Customer
import ru.hse.restaurant.reader.ConsoleUserReader
import ru.hse.restaurant.view.ConsoleOutputHandler
import org.mindrot.jbcrypt.BCrypt

class RegisterCommand(
    private val userData: UserData,
    private val reader: ConsoleUserReader = ConsoleUserReader(),
    private val outputHandler: ConsoleOutputHandler = ConsoleOutputHandler(),
) : RestaurantProcess {
    override fun process(restaurantInfo: RestaurantInfo) {
        execute(restaurantInfo)
    }

    private fun execute(info: RestaurantInfo) {
        if (info.currSession.checkLogin()) {
            outputHandler.showMessage("Вы уже зарегистрированы")
        } else {
            outputHandler.showMessage("Введите логин:")
            val login = reader.readAuthData()

            if (userData.checkUser(login)) {
                outputHandler.showMessage("Пользователь с таким логином уже существует")
                return
            }

            outputHandler.showMessage("Введите пароль:")
            val password = reader.readAuthData()

            outputHandler.showMessage("Введите специальный код (для регистрации администратора):")
            val adminCode = reader.readAuthData()

            if (adminCode == "1234") {
                val admin = Administrator(login, BCrypt.hashpw(password, BCrypt.gensalt()))
                userData.addAdmin(admin)
                info.currSession.login(admin)
            } else {
                val customer = Customer(login, BCrypt.hashpw(password, BCrypt.gensalt()))
                userData.addCustomer(customer)
                info.currSession.login(customer)
            }

            outputHandler.showMessage("Регистрация выполнена успешно.")
        }
    }
}

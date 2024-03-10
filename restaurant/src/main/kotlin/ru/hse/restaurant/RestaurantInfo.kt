package ru.hse.restaurant

import ru.hse.restaurant.autherization.CurrentSession
import ru.hse.restaurant.autherization.UserData
import ru.hse.restaurant.controller.LoginCommand
import ru.hse.restaurant.controller.LogoutCommand
import ru.hse.restaurant.controller.RegisterCommand
import ru.hse.restaurant.controller.RestaurantProcess
import ru.hse.restaurant.controller.customer.AddMoneyCommand
import ru.hse.restaurant.controller.customer.CreateOrderCommand
import ru.hse.restaurant.controller.customer.PayForOrderCommand
import ru.hse.restaurant.model.Restaurant
import ru.hse.restaurant.repository.RestaurantRepository
import ru.hse.restaurant.repository.StoragePathValidator
import ru.hse.restaurant.view.ConsoleOutputHandler
import ru.hse.restaurant.controller.processItems.*

class RestaurantInfo(
    var restaurant: Restaurant = Restaurant(),
    var currSession: CurrentSession = CurrentSession(),
    var userData: UserData = UserData(),
    var commands: MutableMap<String, RestaurantProcess> = mutableMapOf(),
    var restaurantPath: String = "",
    var usersPath: String = "",
) {
    fun isFilledInfo(): Boolean {
        val storagePathValidator = StoragePathValidator()
        val (restaurantPath, usersPath) = storagePathValidator.validatePaths()

        if (restaurantPath == null || usersPath == null) {
            ConsoleOutputHandler().showError("Некорректные пути к файлам")
            return false
        }
        this.restaurantPath = restaurantPath
        this.usersPath = usersPath
        val repository = RestaurantRepository(restaurantPath, usersPath)
        val loadedRestaurant = repository.loadRestaurant()
        val loadedUserStorage = repository.loadUserStorage()

        userData.admins.addAll(loadedUserStorage?.admins ?: mutableListOf())
        userData.customers.addAll(loadedUserStorage?.customers ?: mutableListOf())

        restaurant.getOrderManager().stopAllChefs()
        restaurant = loadedRestaurant

        return true
    }


    init {
        commands["register"] = RegisterCommand(userData)
        commands["login"] = LoginCommand(userData)
        commands["logout"] = LogoutCommand()
        commands["addItem"] = AddItemController()
        commands["deleteItem"] = DeleteItemController()
        commands["changeQuantity"] = ChangeCountController()
        commands["seeStatistics"] = SeeStatisticsController()
        commands["createOrder"] = CreateOrderCommand()
        commands["payFor"] = PayForOrderCommand()
        commands["addMoney"] = AddMoneyCommand()

    }
}
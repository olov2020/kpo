package ru.hse.restaurant.controller.validation

import ru.hse.restaurant.RestaurantInfo
import ru.hse.restaurant.repository.RestaurantRepository

class ExecutionCommandHandler : CommandHandler {
    override fun handle(command: String, info: RestaurantInfo): Boolean {
        if (command == "exit") {
            info.restaurant.getOrderManager().stopAllChefs()
            RestaurantRepository(info.restaurantPath, info.usersPath).saveUserStorage(info.userData)
            RestaurantRepository(info.restaurantPath, info.usersPath).saveRestaurant(info.restaurant)
            return false
        }
        info.commands[command]!!.process(info)
        return true
    }
}
package ru.hse.restaurant.controller.validation

import ru.hse.restaurant.RestaurantInfo

interface CommandHandler {
    fun handle(command: String, info: RestaurantInfo): Boolean
}
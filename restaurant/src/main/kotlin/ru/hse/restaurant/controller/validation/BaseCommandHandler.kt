package ru.hse.restaurant.controller.validation

import ru.hse.restaurant.RestaurantInfo

abstract class BaseCommandHandler(private val nextHandler: CommandHandler?) : CommandHandler {
    override fun handle(command: String, info: RestaurantInfo): Boolean {
        return nextHandler?.handle(command, info) ?: false
    }
}
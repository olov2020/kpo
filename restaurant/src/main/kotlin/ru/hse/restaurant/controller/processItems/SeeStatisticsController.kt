package ru.hse.restaurant.controller.processItems

import ru.hse.restaurant.RestaurantInfo
import ru.hse.restaurant.controller.RestaurantProcess
import ru.hse.restaurant.view.ConsoleOutputHandler

class SeeStatisticsController(
    private val outputHandler: ConsoleOutputHandler = ConsoleOutputHandler(),
) : RestaurantProcess {

    override fun process(restaurantInfo: RestaurantInfo) {
        outputHandler.showStatistics(restaurantInfo.restaurant.getStatistics())
    }
}

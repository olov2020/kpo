package ru.hse.restaurant.controller

import ru.hse.restaurant.RestaurantInfo

interface RestaurantProcess {
    fun process(restaurantInfo: RestaurantInfo)
}
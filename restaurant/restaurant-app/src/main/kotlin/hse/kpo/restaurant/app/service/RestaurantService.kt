package hse.kpo.restaurant.app.service

import hse.kpo.restaurant.app.dto.Order

interface RestaurantService {
    fun findOrders(name: String?, ids: List<Long?>?): List<Order?>?

    fun getOrderById(id: Long?): Order?

    fun createOrder(order: Order?): Order?

    fun editOrder(id: Long?, order: Order?)

    fun deleteOrder(id: Long?)
}
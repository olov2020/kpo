package hse.kpo.restaurant.api

import hse.kpo.restaurant.api.dto.Order
import hse.kpo.restaurant.api.dto.OrderList

interface RestaurantApi {
    fun findOrders(name: String?, ids: List<Long?>?): OrderList?

    fun getOrderById(id: Long?): Order?

    fun createOrder(order: Order?): Order?

    fun editOrder(id: Long?, order: Order?)

    fun deleteOrder(id: Long?)
}
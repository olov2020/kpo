package hse.kpo.restaurant.app.service

import hse.kpo.restaurant.api.dto.ItemList
import hse.kpo.restaurant.app.dto.Item
import hse.kpo.restaurant.app.dto.Order

interface OrderService {
    fun findOrders(name: String?, ids: List<Long?>?): List<Order?>?

    fun getOrderById(id: Long?): Order?

    fun createOrder(item: Item?): Order?

    fun editOrder(id: Long?, order: Item?)

    fun deleteOrder(id: Long?)
    fun deleteItem(id: Long?, item: hse.kpo.restaurant.api.dto.Item?)
}
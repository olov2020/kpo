package hse.kpo.restaurant.api

import hse.kpo.restaurant.api.dto.Item
import hse.kpo.restaurant.api.dto.ItemList
import hse.kpo.restaurant.api.dto.Order
import hse.kpo.restaurant.api.dto.OrderList

interface OrderApi {

    fun getOrderById(id: Long?): Order?

    fun createOrder(item: Item?): Order?

    fun editOrder(id: Long?, item: Item?)

    fun deleteItem(id: Long?, item: Item?)

    fun deleteOrder(id: Long?)
}
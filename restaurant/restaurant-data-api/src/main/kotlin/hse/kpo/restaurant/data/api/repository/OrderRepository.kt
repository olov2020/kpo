package hse.kpo.restaurant.data.api.repository

import hse.kpo.restaurant.data.api.model.Item
import hse.kpo.restaurant.data.api.model.Order

interface OrderRepository {
    fun findByName(name: String?): List<Order?>?

    fun findByNameAndIdIn(name: String?, ids: List<Long?>?): List<Order?>?

    fun findById(id: Long?): Order?

    fun existsById(id: Long?): Boolean

    fun save(order: Order?): Order?

    fun deleteById(id: Long?)

    fun deleteItemById(item: Item?)

    fun updateById(order: Order?)
}
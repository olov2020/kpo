package hse.kpo.restaurant.data.repository

import hse.kpo.restaurant.data.api.model.Order
import hse.kpo.restaurant.data.api.repository.OrderRepository

class OrderRepositoryImpl : OrderRepository {
    override fun findByName(name: String?): List<Order?>? {
        TODO("Not yet implemented")
    }

    override fun findByNameAndIdIn(name: String?, ids: List<Long?>?): List<Order?>? {
        TODO("Not yet implemented")
    }

    override fun findById(id: Long?): Order? {
        TODO("Not yet implemented")
    }

    override fun existsById(id: Long?): Boolean {
        TODO("Not yet implemented")
    }

    override fun save(order: Order?): Order? {
        TODO("Not yet implemented")
    }

    override fun deleteById(id: Long?) {
        TODO("Not yet implemented")
    }

    override fun updateById(order: Order?) {
        TODO("Not yet implemented")
    }
}
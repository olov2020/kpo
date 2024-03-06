package hse.kpo.restaurant.app.service

import hse.kpo.restaurant.app.dto.Item
import hse.kpo.restaurant.app.dto.Order
import hse.kpo.restaurant.app.exception.OrderNotFoundById
import hse.kpo.restaurant.app.mapper.ItemMapper
import hse.kpo.restaurant.app.mapper.OrderMapper
import hse.kpo.restaurant.data.api.repository.OrderRepository

abstract class OrderServiceLmpl : OrderService {
    private val orderRepository: OrderRepository? = null

    private val orderMapper: OrderMapper? = null

    private val itemMapper: ItemMapper? = null

    override fun getOrderById(id: Long?): Order? {
        if (!orderRepository?.existsById(id)!!) {
            throw OrderNotFoundById(id)
        }

        return orderMapper?.dataModel2AppDto(orderRepository.findById(id))
    }

    override fun createOrder(item: Item?): Order? {
        return orderMapper?.dataModel2AppDto(
            orderRepository?.save(itemMapper?.appDto2DataModel(null, item))
        )
    }

    override fun editOrder(id: Long?, item: Item?) {
        if (!orderRepository?.existsById(id)!!) {
            throw OrderNotFoundById(id)
        }

        orderRepository.updateById(itemMapper?.appDto2DataModel(id, item))
    }

    override fun deleteOrder(id: Long?) {
        if (!orderRepository?.existsById(id)!!) {
            throw OrderNotFoundById(id)
        }

        orderRepository.deleteById(id)
    }

    /*override fun deleteItem(id: Long?, item: Item?) {
        if (!orderRepository?.existsById(id)!!) {
            throw OrderNotFoundById(id)
        }

        orderRepository.deleteItemById(itemMapper?.apiDto2AppDto(id, item))
    }*/
}
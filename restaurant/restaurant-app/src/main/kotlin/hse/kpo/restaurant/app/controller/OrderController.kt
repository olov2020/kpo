package hse.kpo.restaurant.app.controller

import hse.kpo.restaurant.api.OrderApi
import hse.kpo.restaurant.api.dto.Item
import hse.kpo.restaurant.api.dto.ItemList
import hse.kpo.restaurant.api.dto.OrderList
import hse.kpo.restaurant.api.dto.Order
import hse.kpo.restaurant.app.mapper.OrderMapper
import hse.kpo.restaurant.app.mapper.ItemMapper
import hse.kpo.restaurant.app.service.OrderService
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/api/v1/restaurant")
abstract class OrderController : OrderApi {
    private val orderService: OrderService? = null

    private val itemMapper: ItemMapper? = null

    private val orderMapper: OrderMapper? = null

    @GetMapping("/{id}")
    override fun getOrderById(@PathVariable id: Long?): Order? {
        return orderMapper?.appDto2ApiDto(orderService?.getOrderById(id))
    }

    @PostMapping
    override fun createOrder(item: Item?): Order? {
        return orderMapper?.appDto2ApiDto(
            orderService?.createOrder(itemMapper?.apiDto2AppDto(item))
        )
    }

    @PutMapping("/{id}")
    @ResponseStatus(org.springframework.http.HttpStatus.NO_CONTENT)
    override fun editOrder(id: Long?, item: Item?) {
        orderService?.editOrder(id, itemMapper?.apiDto2AppDto(item))
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(org.springframework.http.HttpStatus.NO_CONTENT)
    override fun deleteOrder(@PathVariable id: Long?) {
        orderService?.deleteOrder(id)
    }

    /*@DeleteMapping("/{id}")
    @ResponseStatus(org.springframework.http.HttpStatus.NO_CONTENT)
    override fun deleteItem(@PathVariable id: Long?, item: Item?) {
        orderService?.deleteItem(id, item)
    }*/
}
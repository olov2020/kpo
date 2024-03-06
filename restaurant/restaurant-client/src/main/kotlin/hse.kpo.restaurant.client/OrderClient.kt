import hse.kpo.restaurant.api.OrderApi
import hse.kpo.restaurant.api.dto.Item
import hse.kpo.restaurant.api.dto.Order
import hse.kpo.restaurant.api.dto.OrderList
import org.springframework.web.bind.annotation.*

interface OrderClient : OrderApi {

    @GetMapping("/api/v1/order/{id}")
    override fun getOrderById(@PathVariable(name = "id") id: Long?): Order?

    @PostMapping("/api/v1/order")
    override fun createOrder(item: Item?): Order?

    @PutMapping("/api/v1/order/{id}")
    override fun editOrder(id: Long?, item: Item?)

    @DeleteMapping("/api/v1/order/{id}")
    override fun deleteOrder(@PathVariable(name = "id") id: Long?)

    @DeleteMapping("/api/v1/order/{id}")
    override fun deleteItem(@PathVariable(name = "id") id: Long?, item: Item?)
}
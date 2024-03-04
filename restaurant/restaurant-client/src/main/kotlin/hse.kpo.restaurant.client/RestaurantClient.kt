import hse.kpo.restaurant.api.RestaurantApi
import hse.kpo.restaurant.api.dto.Order
import hse.kpo.restaurant.api.dto.OrderList
import org.springframework.web.bind.annotation.*

interface RestaurantClient : RestaurantApi {
    @GetMapping("/api/v1/product")
    override fun findOrders(
        @RequestParam(required = false, name = "name", defaultValue = "") name: String?,
        @RequestParam(required = false, name = "id") ids: List<Long?>?
    ): OrderList?

    @GetMapping("/api/v1/product/{id}")
    override fun getOrderById(@PathVariable(name = "id") id: Long?): Order?

    @PostMapping("/api/v1/product")
    override fun createOrder(@RequestBody order: Order?): Order?

    @PutMapping("/api/v1/product/{id}")
    override fun editOrder(@PathVariable(name = "id") id: Long?, @RequestBody order: Order?)

    @DeleteMapping("/api/v1/product/{id}")
    override fun deleteOrder(@PathVariable(name = "id") id: Long?)
}
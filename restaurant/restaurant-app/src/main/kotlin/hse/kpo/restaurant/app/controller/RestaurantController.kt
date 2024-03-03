package hse.kpo.restaurant.app.controller

import hse.kpo.restaurant.api.RestaurantApi
import org.springframework.web.bind.annotation.*
import hse.kpo.restaurant.api.dto.Order
import hse.kpo.restaurant.app.service.RestaurantService


@RestController
@RequestMapping("/api/v1/restaurant")
class RestaurantController : RestaurantApi {
    private val restaurantService: RestaurantService? = null

    private val productMapper: ProductMapper? = null

    private val detailedProductMapper: DetailedProductMapper? = null

    @GetMapping
    fun findProducts(
        @RequestParam(required = false, defaultValue = "") name: String?,
        @RequestParam(required = false, name = "id") ids: List<UUID?>?
    ): ProductList {
        return ProductList(
            productService.findProducts(name, ids).stream().map(productMapper::appDto2ApiDto).toList()
        )
    }

    @GetMapping("/{id}")
    fun getProductById(@PathVariable id: UUID?): DetailedProduct {
        return detailedProductMapper.appDto2ApiDto(productService.getProductById(id))
    }

    @PostMapping
    fun createProduct(@RequestBody detailedProduct: DetailedProduct?): Product {
        return productMapper.appDto2ApiDto(
            productService.createProduct(detailedProductMapper.apiDto2AppDto(detailedProduct))
        )
    }

    @PutMapping("/{id}")
    @org.springframework.web.bind.annotation.ResponseStatus(org.springframework.http.HttpStatus.NO_CONTENT)
    fun editProduct(@PathVariable id: UUID?, @RequestBody detailedProduct: DetailedProduct?) {
        productService.editProduct(id, detailedProductMapper.apiDto2AppDto(detailedProduct))
    }

    @DeleteMapping("/{id}")
    @org.springframework.web.bind.annotation.ResponseStatus(org.springframework.http.HttpStatus.NO_CONTENT)
    fun deleteProduct(@PathVariable id: UUID?) {
        productService.deleteProduct(id)
    }
}
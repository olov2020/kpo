package hse.kpo.restaurant.app.mapper

import hse.kpo.restaurant.app.dto.Order

interface RestaurantMapper {
    fun appDto2ApiDto(
        product: Order?
    ): Order?

    fun dataModel2AppDto(
        product: ru.hse.product.storage.data.api.model.Product?
    ): ru.hse.product.storage.app.dto.Product?
}
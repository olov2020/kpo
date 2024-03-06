package hse.kpo.restaurant.app.mapper

interface OrderMapper {
    fun dataModel2AppDto(
        order: hse.kpo.restaurant.data.api.model.Order?
    ): hse.kpo.restaurant.app.dto.Order?

    fun appDto2ApiDto(
        order: hse.kpo.restaurant.app.dto.Order?
    ): hse.kpo.restaurant.api.dto.Order?
}

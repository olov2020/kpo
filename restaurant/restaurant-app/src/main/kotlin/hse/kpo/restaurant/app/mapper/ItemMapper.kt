package hse.kpo.restaurant.app.mapper

interface ItemMapper {
    fun apiDto2AppDto(
        item: hse.kpo.restaurant.api.dto.Item?
    ): hse.kpo.restaurant.app.dto.Item?

    fun appDto2DataModel(
        id: Long?, detailedProduct: hse.kpo.restaurant.app.dto.Item?
    ): hse.kpo.restaurant.data.api.model.Order?

    fun appDto2ApiDto(
        item: hse.kpo.restaurant.app.dto.Item?
    ): hse.kpo.restaurant.api.dto.Item?

    fun dataModel2AppDto(
        order: hse.kpo.restaurant.data.api.model.Order?
    ): hse.kpo.restaurant.app.dto.Item?
}
package hse.kpo.restaurant.app.service

abstract class RestaurantServiceLmpl : RestaurantService {
    private val restaurantRepository: RestaurantRepository? = null

    private val restaurantMapper: RestaurantMapper? = null

    private val detailedProductMapper: DetailedProductMapper? = null

    fun findProducts(name: String?, ids: List<UUID?>?): List<Product> {
        val list: Unit = if (org.springframework.util.CollectionUtils.isEmpty(ids)
        ) productRepository.findByName(name)
        else productRepository.findByNameAndIdIn(name, ids)

        return list.stream()
            .map(productMapper::dataModel2AppDto)
            .toList()
    }

    fun getProductById(id: UUID?): DetailedProduct {
        if (!productRepository.existsById(id)) {
            throw ProductNotFoundById(id)
        }

        return detailedProductMapper.dataModel2AppDto(productRepository.findById(id))
    }

    fun createProduct(detailedProduct: DetailedProduct?): Product {
        return productMapper.dataModel2AppDto(
            productRepository.save(detailedProductMapper.appDto2DataModel(null, detailedProduct))
        )
    }

    fun editProduct(id: UUID?, detailedProduct: DetailedProduct?) {
        if (!productRepository.existsById(id)) {
            throw ProductNotFoundById(id)
        }

        productRepository.updateById(detailedProductMapper.appDto2DataModel(id, detailedProduct))
    }

    fun deleteProduct(id: UUID?) {
        if (!productRepository.existsById(id)) {
            throw ProductNotFoundById(id)
        }

        productRepository.deleteById(id)
    }
}
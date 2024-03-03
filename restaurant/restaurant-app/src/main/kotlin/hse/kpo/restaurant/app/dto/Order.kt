package hse.kpo.restaurant.app.dto

import hse.kpo.restaurant.api.dto.ItemList

class Order {
    private val id: Long
        get () {
            return this.id
        }

    private val itemList: ItemList
        get () {
            return this.itemList
        }

    private val price: Long
        get () {
            return this.price
        }
}
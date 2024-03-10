package ru.hse.restaurant.model

class MenuItem {
    companion object {
        fun createItem(name: String, price: Long, cookingTime: Long, count: Long): Item {
            return Item(name, price, cookingTime, count)
        }
    }
}
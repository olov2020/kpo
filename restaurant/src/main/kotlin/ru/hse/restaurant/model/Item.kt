package ru.hse.restaurant.model

import com.fasterxml.jackson.databind.annotation.JsonSerialize

data class Item(
    @JsonSerialize
    private val name: String? = null,
    @JsonSerialize
    private var price: Long = 1,
    @JsonSerialize
    private var cookingTime: Long = 1,
    @JsonSerialize
    private var count: Long? = null,
) {
    override fun toString(): String {
        return ("$name: price - $price, cooking time - $cookingTime, count - $count")
    }

    fun getPrice(): Long {
        return price
    }

    fun getName(): String? {
        return name
    }

    fun getCount(): Long? {
        return count
    }

    fun setCount(newCount: Long?) {
        count = newCount
    }

    fun getCookingTime(): Long {
        return cookingTime
    }
}
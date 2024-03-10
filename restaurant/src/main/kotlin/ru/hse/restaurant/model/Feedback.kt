package ru.hse.restaurant.model

import com.fasterxml.jackson.databind.annotation.JsonSerialize

class Feedback(
    @JsonSerialize
    private val stars: Int = 1,
    @JsonSerialize
    private val comment: String = "",
    @JsonSerialize
    private val order: Order,
) {
    fun getComment(): String {
        return comment
    }

    fun getStars(): Int {
        return stars
    }

    fun getOrder(): Order {
        return order
    }
}
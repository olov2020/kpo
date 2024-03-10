package ru.hse.restaurant.model

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.databind.annotation.JsonSerialize

class Statistics(
    @JsonSerialize
    private val feedbacks: MutableList<Feedback> = mutableListOf(),
) {
    fun addFeedback(rating: Int, description: String, order: Order) {
        val review = Feedback(rating, description, order)
        feedbacks.add(review)
    }

    fun getFeedbacks(): MutableList<Feedback> {
        return feedbacks
    }

    @JsonIgnore
    fun getRestaurantStars(): Double {
        if (feedbacks.isEmpty()) {
            return 0.0
        }
        val sum = feedbacks.sumOf { it.getStars() }
        return sum.toDouble() / feedbacks.size
    }

    @JsonIgnore
    fun getAverageStars(): Map<Item, Double> {
        val itemStars = mutableMapOf<Item, MutableList<Int>>()

        feedbacks.forEach { feedback ->
            feedback.getOrder().getItems()?.forEach { item ->
                val stars = itemStars.getOrPut(item) { mutableListOf() }
                stars.add(feedback.getStars())
            }
        }

        val averageStars = itemStars.mapValues { (_, ratings) ->
            ratings.average()
        }

        return averageStars
            .toList()
            .sortedByDescending { it.second }
            .toMap()
    }

    @JsonIgnore
    fun getPopularItemsByCount(): Map<Item, Int> {
        val itemsCount = mutableMapOf<Item, Int>()

        feedbacks.forEach { feedback ->
            feedback.getOrder().getItems().forEach { item ->
                itemsCount[item] = itemsCount.getOrDefault(item, 0) + 1
            }
        }

        return itemsCount
            .toList()
            .sortedByDescending { it.second }
            .toMap()
    }
}
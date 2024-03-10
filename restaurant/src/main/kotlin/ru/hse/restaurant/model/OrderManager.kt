package ru.hse.restaurant.model

import java.util.concurrent.BlockingQueue
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.concurrent.LinkedBlockingQueue

class OrderManager(
    private val executor: ExecutorService = Executors.newFixedThreadPool(3),
    private val cookingItems: MutableList<Cooking> = mutableListOf(),
    private val orderQueue: BlockingQueue<Order> = LinkedBlockingQueue(),
) {
    init {
        repeat(3) {
            val cooking = Cooking(orderQueue)
            cookingItems.add(cooking)
            executor.submit(cooking)
        }
    }

    fun processOrder(order: Order) {
        orderQueue.put(order)
    }

    fun stopAllChefs() {
        for (cooking in cookingItems) {
            cooking.stop()
        }
        executor.shutdown()
    }
}
package ru.hse.restaurant.model

import java.util.concurrent.BlockingQueue

class Cooking(
    private val orderQueue: BlockingQueue<Order>,
    @Volatile
    var shouldStop: Boolean = false,
) : Runnable {
    override fun run() {
        while (!shouldStop) {
            val order = orderQueue.poll() ?: continue
            processOrder(order)
        }
    }

    private fun processOrder(order: Order) {
        order.setStatus(OrderStatus.PROCESSING)

        Thread.sleep(order.getCookingTime() * 60 * 1000)

        order.setStatus(OrderStatus.READY)
    }

    fun stop() {
        shouldStop = true
    }
}
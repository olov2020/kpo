package ru.hse.restaurant.model

import java.util.concurrent.BlockingQueue
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.concurrent.LinkedBlockingQueue

class OrderManager(
    private val executor: ExecutorService = Executors.newFixedThreadPool(3),
    private val chefs: MutableList<Cooking> = mutableListOf(),
    private val orderQueue: BlockingQueue<Order> = LinkedBlockingQueue(),
) {
    init {
        repeat(3) {
            val chef = Cooking(orderQueue)
            chefs.add(chef)
            executor.submit(chef)
        }
    }

    fun processOrder(order: Order) {
        orderQueue.put(order)
    }

    fun stopAllChefs() {
        for (chef in chefs) {
            chef.stop()
        }
        executor.shutdown()
    }
}
package ru.hse.restaurant.model

import ru.hse.restaurant.view.ConsoleOutputHandler

interface OrderObserver {
    fun updateOrderStatus(order: Order)
}

class Customer(
    private val login: String = "",
    private val hashedPassword: String = "",
    private var balance: Long = 100,
) : User, OrderObserver {
    override fun getLogin(): String {
        return login
    }

    override fun getHashedPassword(): String {
        return hashedPassword
    }

    override fun updateOrderStatus(order: Order) {
        val outputHandler = ConsoleOutputHandler()
        outputHandler.showOrderStatus(order)
    }

    fun getBalance(): Long {
        return balance
    }

    fun setBalance(newBalance: Long) {
        balance = newBalance
    }

    fun payOrder(payment: Long): Boolean {
        return if (balance < payment) {
            false
        } else {
            balance -= payment
            true
        }
    }
}
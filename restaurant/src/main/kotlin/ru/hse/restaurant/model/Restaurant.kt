package ru.hse.restaurant.model

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import java.io.IOException
import java.io.ObjectInputStream
import java.io.ObjectOutputStream
import java.io.Serializable

class Restaurant : Serializable {
    @JsonIgnore
    private val orders: MutableList<Order> = mutableListOf()

    @JsonIgnore
    private val orderManager: OrderManager = OrderManager()

    @Volatile
    private var amountOfRevenue: Long = 0

    @JsonSerialize
    private var menu: Menu = Menu()

    @JsonSerialize
    private var statistics: Statistics = Statistics()

    fun getMenu(): Menu {
        return menu
    }

    fun setMenu(menu: Menu) {
        this.menu = menu
    }

    fun getStatistics(): Statistics {
        return statistics
    }

    fun setStatistics(newStatistics: Statistics) {
        statistics = newStatistics
    }

    fun getAmountOfRevenue(): Long {
        return amountOfRevenue
    }

    fun setAmountOfRevenue(newAmount: Long) {
        amountOfRevenue = newAmount
    }

    fun getOrders(): MutableList<Order> {
        return orders
    }

    fun getOrderManager(): OrderManager {
        return orderManager
    }

    @Throws(IOException::class)
    private fun writeObject(out: ObjectOutputStream) {
        out.defaultWriteObject()
        out.writeObject(menu)
        out.writeObject(statistics)
        out.writeLong(amountOfRevenue)
    }

    @Throws(IOException::class, ClassNotFoundException::class)
    private fun readObject(`in`: ObjectInputStream) {
        `in`.defaultReadObject()
        menu = `in`.readObject() as Menu
        statistics = `in`.readObject() as Statistics
        amountOfRevenue = `in`.readLong()
    }
}
package ru.hse.restaurant.model

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import java.io.Serializable

interface Observable {
    fun registerObserver(observer: OrderObserver)
    fun notifyObserver()
}

class Order(
    @JsonSerialize
    private var customer: Customer,
    @JsonSerialize
    private var items: MutableList<Item> = mutableListOf(),
    @JsonIgnore
    private var status: OrderStatus = OrderStatus.CREATED,
    @JsonIgnore
    private var observer: OrderObserver? = null,
) : Observable, Serializable {
    fun getItems(): MutableList<Item> {
        return items
    }

    fun addItem(item: Item) {
        val checkItem = items.find { it.getName() == item.getName() }
        if (checkItem != null) {
            checkItem.setCount(checkItem.getCount()!! + 1)
        } else {
            items.add(item.copy(count = 1))
        }
    }

    @JsonIgnore
    fun getTotalCost(): Long {
        return items.sumOf { item ->
            item.getPrice() * item.getCount()!!
        }
    }

    @JsonIgnore
    fun getCookingTime(): Long {
        return items.sumOf { item ->
            item.getCookingTime() * (item.getCount()!!)
        }
    }

    fun setStatus(newStatus: OrderStatus) {
        status = newStatus
        notifyObserver()
    }

    fun getStatus(): OrderStatus {
        return status
    }

    fun getCustomer(): Customer {
        return customer
    }

    override fun notifyObserver() {
        observer?.updateOrderStatus(this)
    }

    override fun registerObserver(observer: OrderObserver) {
        this.observer = observer
    }

    fun showOrderStatus(): String {
        val statusString = when (status) {
            OrderStatus.CREATED -> "created"
            OrderStatus.ACCEPTED -> "accepted"
            OrderStatus.PROCESSING -> "processing"
            OrderStatus.READY -> "ready"
            else -> {
                return "Unknown status"
            }
        }
        return "Заказ для ${customer.getLogin()}. Статус заказа: $statusString"
    }

    override fun toString(): String {
        val builder = StringBuilder()
        builder.appendLine("--------------------------------------------------")
        builder.appendLine("|Заказ для ${customer.getLogin()}|")
        builder.appendLine("--------------------------------------------------")
        builder.appendLine("| Название       | Цена    | Количество | Подитог  |")
        builder.appendLine("--------------------------------------------------")
        items.forEach { item ->
            val name = item.getName()
            val price = item.getPrice()
            val count = item.getCount().toString()
            val subPrice = (item.getPrice() * item.getCount()!!).toString()
            builder.appendLine("| $name| $price| $count| $subPrice|")
        }
        builder.appendLine("--------------------------------------------------")
        builder.appendLine("|                                      Итого: ${getTotalCost()}|")
        builder.appendLine("--------------------------------------------------")
        return builder.toString()
    }
}
package ru.hse.restaurant.model

sealed class OrderStatus {
    data object CREATED : OrderStatus()
    data object ACCEPTED : OrderStatus()
    data object PROCESSING : OrderStatus()
    data object READY : OrderStatus()
}
package ru.hse.restaurant.model

class OrderStatusImpl : OrderStatus() {
    companion object {
        fun values(): Array<OrderStatus> {
            return arrayOf(CREATED, ACCEPTED, PROCESSING, READY)
        }

        fun valueOf(value: String): OrderStatus {
            return when (value) {
                "CREATED" -> CREATED
                "ACCEPTED" -> ACCEPTED
                "PROCESSING" -> PROCESSING
                "READY" -> READY
                else -> throw IllegalArgumentException("No object ru.hse.restaurant.model.OrderStatus.$value")
            }
        }
    }
}
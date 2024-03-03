package hse.kpo.restaurant.app.exception

class OrderNotFoundById(id: Long) : RuntimeException("Order with id '$id' not found")
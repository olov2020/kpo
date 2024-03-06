package hse.kpo.restaurant.app.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.NOT_FOUND)
class OrderNotFoundById(id: Long) : RuntimeException("Order with id '$id' not found")
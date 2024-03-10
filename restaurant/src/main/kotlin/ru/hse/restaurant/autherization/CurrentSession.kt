package ru.hse.restaurant.autherization

import ru.hse.restaurant.model.Administrator
import ru.hse.restaurant.model.User

class CurrentSession(
    private var currUser: User? = null,
) {
    fun login(user: User) {
        currUser = user
    }

    fun logout() {
        currUser = null
    }

    fun checkLogin(): Boolean {
        return currUser != null
    }

    fun checkAdmin(): Boolean {
        return currUser is Administrator
    }

    fun getUser(): User? {
        return currUser
    }
}
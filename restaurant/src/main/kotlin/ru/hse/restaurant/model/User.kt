package ru.hse.restaurant.model

interface User {
    fun getLogin(): String
    fun getHashedPassword(): String
}
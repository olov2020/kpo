package ru.hse.restaurant.repository

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.readValue
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import ru.hse.restaurant.autherization.UserData
import ru.hse.restaurant.model.Restaurant
import ru.hse.restaurant.view.ConsoleOutputHandler
import java.io.File
import java.io.IOException

class RestaurantRepository(
    private var pathToRestaurantFile: String,
    private var pathToUserStorageFile: String,
    private val objectMapper: ObjectMapper = ObjectMapper().registerKotlinModule().registerModule(JavaTimeModule()),
) {
    fun saveRestaurant(restaurant: Restaurant) {
        try {
            val jsonString = objectMapper.writeValueAsString(restaurant)
            File(pathToRestaurantFile).writeText(jsonString)
        } catch (e: IOException) {
            val outputHandler = ConsoleOutputHandler()
            outputHandler.showError("Проблема с файлом: $e")
        }
    }

    fun loadRestaurant(): Restaurant {
        return try {
            val jsonString = File(pathToRestaurantFile).readText()
            objectMapper.readValue(jsonString)
        } catch (e: IOException) {
            val outputHandler = ConsoleOutputHandler()
            outputHandler.showError("Проблема с чтением файла: $e")
            Restaurant()
        }
    }

    fun saveUserStorage(userData: UserData) {
        try {
            val jsonString = objectMapper.writeValueAsString(userData)
            File(pathToUserStorageFile).writeText(jsonString)
        } catch (e: IOException) {
            val outputHandler = ConsoleOutputHandler()
            outputHandler.showError("Проблема с файлом: $e")
        }
    }

    fun loadUserStorage(): UserData? {
        return try {
            val jsonString = File(pathToUserStorageFile).readText()
            return objectMapper.readValue<UserData>(jsonString, UserData::class.java)
        } catch (e: IOException) {
            val outputHandler = ConsoleOutputHandler()
            outputHandler.showError("Проблема с чтением файла: $e")
            return UserData()
        }
    }
}
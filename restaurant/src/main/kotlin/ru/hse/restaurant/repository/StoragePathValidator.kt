package ru.hse.restaurant.repository

import ru.hse.restaurant.view.ConsoleOutputHandler
import ru.hse.restaurant.reader.ConsoleUserReader
import ru.hse.restaurant.reader.UserReader
import java.io.File

class StoragePathValidator(
    private val outputHandler: ConsoleOutputHandler = ConsoleOutputHandler(),
    private val reader: UserReader = ConsoleUserReader(),
) {

    fun validatePaths(): Pair<String?, String?> {
        outputHandler.showMessage("Введите путь к файлу с данными ресторана:")
        val restaurantPath = reader.readString()
        if (restaurantPath == null || !File(restaurantPath).exists()) {
            outputHandler.showError("Некорректный путь к файлу ресторана.")
            return Pair(null, null)
        }

        outputHandler.showMessage("Введите путь к файлу с данными пользователей:")
        val usersPath = reader.readString()
        if (usersPath == null || !File(usersPath).exists()) {
            outputHandler.showError("Некорректный путь к файлу пользователей.")
            return Pair(null, null)
        }

        return Pair(restaurantPath, usersPath)
    }
}

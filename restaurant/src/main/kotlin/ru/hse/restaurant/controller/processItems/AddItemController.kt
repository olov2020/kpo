package ru.hse.restaurant.controller.processItems

import ru.hse.restaurant.controller.RestaurantProcess
import ru.hse.restaurant.RestaurantInfo
import ru.hse.restaurant.model.MenuItem
import ru.hse.restaurant.reader.ConsoleUserReader
import ru.hse.restaurant.view.ConsoleOutputHandler

class AddItemController(
    private val reader: ConsoleUserReader = ConsoleUserReader(),
    private val outputHandler: ConsoleOutputHandler = ConsoleOutputHandler(),
) : RestaurantProcess {

    override fun process(restaurantInfo: RestaurantInfo) {
        outputHandler.showMenuAllBase(restaurantInfo)
        outputHandler.showMessage("Введите название новой позиции:")
        val name = reader.readString()
        outputHandler.showMessage("Введите цену:")
        val price = reader.readInt()?.toLong()
        outputHandler.showMessage("Введите время приготовления (в минутах):")
        val cookingTime = reader.readInt()?.toLong()
        outputHandler.showMessage("Введите количество:")
        val count = reader.readInt()?.toLong()

        val currentMenu = restaurantInfo.restaurant.getMenu()

        if (name != null && price != null && cookingTime != null && count != null && !currentMenu.checkMenu(name) && price > 0 && cookingTime > 0 && count > 0) {
            val menuItem = MenuItem.createItem(name, price, cookingTime, count)
            currentMenu.addItem(menuItem)
            outputHandler.showMessage("Позиция успешно добавлена в меню")
        } else {
            outputHandler.showError("Данные некорректны или создаваемая позиция уже есть в меню")
        }
    }
}

package ru.hse.restaurant.controller.processItems

import ru.hse.restaurant.RestaurantInfo
import ru.hse.restaurant.controller.RestaurantProcess
import ru.hse.restaurant.reader.ConsoleUserReader
import ru.hse.restaurant.view.ConsoleOutputHandler

class DeleteItemController(
    private val reader: ConsoleUserReader = ConsoleUserReader(),
    private val outputHandler: ConsoleOutputHandler = ConsoleOutputHandler(),
) : RestaurantProcess {

    override fun process(restaurantInfo: RestaurantInfo) {
        outputHandler.showMenuAllBase(restaurantInfo)
        outputHandler.showMessage("Выберите, что именно вы хотите удалить из меню, введя название позиции:")
        val inputItem = reader.readString()

        val currentMenu = restaurantInfo.restaurant.getMenu()

        if (inputItem != null && currentMenu.checkMenu(inputItem)) {
            currentMenu.deleteItem(inputItem)
            outputHandler.showMessage("Позиция успешно удалена из меню")
        } else {
            outputHandler.showError("Позиция не найдена в меню")
        }
    }
}

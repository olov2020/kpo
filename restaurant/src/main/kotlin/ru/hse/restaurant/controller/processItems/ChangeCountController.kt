package ru.hse.restaurant.controller.processItems

import ru.hse.restaurant.RestaurantInfo
import ru.hse.restaurant.controller.RestaurantProcess
import ru.hse.restaurant.reader.ConsoleUserReader
import ru.hse.restaurant.view.ConsoleOutputHandler

class ChangeCountController(
    private val reader: ConsoleUserReader = ConsoleUserReader(),
    private val outputHandler: ConsoleOutputHandler = ConsoleOutputHandler(),
) : RestaurantProcess {

    override fun process(restaurantInfo: RestaurantInfo) {
        outputHandler.showMenuAllBase(restaurantInfo)
        outputHandler.showMessage("Выберите, у какой именно позиции вы хотите изменить количество, введя ее название:")
        val inputItem = reader.readString()
        outputHandler.showMessage("Введите новое количество:")
        val newCount = reader.readInt()

        val currentMenu = restaurantInfo.restaurant.getMenu()

        if (inputItem != null &&
            newCount != null &&
            newCount > 0 &&
            currentMenu.checkMenu(inputItem)
        ) {
            val count = currentMenu.getItemByName(inputItem)?.getCount()
            currentMenu.getItemByName(inputItem)?.setCount(newCount + count!!)
            outputHandler.showMessage("Количество успешно изменено")
        } else {
            outputHandler.showError("Данные для изменения позиции некорректны")
        }
    }
}

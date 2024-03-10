package ru.hse.restaurant.model

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.databind.annotation.JsonSerialize

data class Menu(
    @JsonSerialize
    var items: MutableList<Item> = mutableListOf(),
) {
    fun addItem(item: Item) {
        items.add(item)
    }

    fun deleteItem(item: String?) {
        val itemToRemove = items.find { it.getName() == item }
        items.remove(itemToRemove)
    }

    fun checkMenu(item: String?): Boolean {
        return items.any { it.getName() == item }
    }

    fun getItemByName(itemName: String): Item? {
        return items.find { it.getName() == itemName }
    }

    @JsonIgnore
    fun showAllMenu(): String {
        val menu = items.toList()

        val showMenu = menu.joinToString(separator = "\n") { it.toString() }

        return "\n$showMenu\n"
    }

    override fun toString(): String {
        val itemsInMenu = items.filter { it.getCount()!! > 0 }
        val showMenu = itemsInMenu.joinToString(separator = "\n") { it.toString() }
        return "\n$showMenu\n"
    }
}
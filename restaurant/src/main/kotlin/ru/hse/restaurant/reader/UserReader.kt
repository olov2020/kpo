package ru.hse.restaurant.reader

interface UserReader {
    fun readInt(): Int?
    fun readString(): String?
    fun readAuthData(): String
}

class ConsoleUserReader : UserReader {
    override fun readInt(): Int? {
        return readlnOrNull()?.toIntOrNull()
    }

    override fun readString(): String? {
        return readlnOrNull()
    }

    override fun readAuthData(): String {
        return readln()
    }
}
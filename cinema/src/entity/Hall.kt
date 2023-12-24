package entity

import source.COLUMNS
import source.ROWS

class Hall {
    private var arr: MutableList<MutableList<String>> = mutableListOf()

    fun setHall(list: MutableList<MutableList<String>>) {
        arr = list
    }

    fun getHall(): MutableList<MutableList<String>> {
        return arr
    }

    fun fill() {
        for (i in 0..<ROWS) {
            var a: MutableList<String> = mutableListOf()
            for (j in 0..<COLUMNS) {
                a.add("o")
            }
            arr.add(a)
        }
    }

    fun buyTickets(seats: MutableList<Pair<Int, Int>>) {
        seats.forEach {
            arr[it.first][it.second] = "x"
        }
    }

    fun returnTickets(seats: MutableList<Pair<Int, Int>>) {
        seats.forEach {
            arr[it.first][it.second] = "o"
        }
    }

    fun toFile(): String {
        var hallString = ""
        arr.forEach {
            hallString += listToFile(it) + ";"
        }
        return hallString
    }

    private fun listToFile(list: MutableList<String>): String {
        var listString = ""
        list.forEach {
            listString += it + ","
        }
        return listString
    }

    override fun toString(): String {
        var hallString = ""
        arr.forEach {
            hallString += it.toString() + "\n"
        }
        return hallString
    }
}
package entity

import source.COLUMNS
import source.ROWS

class Hall {
    private var arr: MutableList<MutableList<Char>> = mutableListOf()

    fun getHall(): MutableList<MutableList<Char>> {
        return arr
    }

    fun fill() {
        for (i in 0..<ROWS) {
            var a: MutableList<Char> = mutableListOf()
            for (j in 0..<COLUMNS) {
                a.add('o')
            }
            arr.add(a)
        }
    }

    fun buyTickets(seats: MutableList<Pair<Int, Int>>) {
        seats.forEach{
            arr[it.first][it.second] = 'x'
        }
    }

    override fun toString(): String {
        var hallString = ""
        arr.forEach {
            hallString += it.toString() + "\n"
        }
        return hallString
    }
}
package entity

import source.SEATS

class Film(
    private val setName: String,
    private val setStart: Int,
    private val setDuration: Int,
    private val setTickets: Int,
) {
    private val name: String = setName
    private val start: Int = setStart
    private val duration: Int = setDuration
    private var tickets: Int = setTickets
    private var hall: MutableList<MutableList<Int>> = mutableListOf(mutableListOf(0))

    fun getHall(): MutableList<MutableList<Int>> {
        return hall
    }

    fun printHall(): String {
        var hallString: String = ""
        hall.forEach {
            hallString += it.toString() + "\n"
        }
        return hallString
    }

    fun getName(): String {
        return name
    }

    fun getStart(): Int {
        return start
    }

    fun getDuration(): Int {
        return duration
    }

    fun getTickets(): Int {
        return tickets
    }

    fun buyTickets(seats: Int, rows: MutableList<Int>, columns: MutableList<Int>) {
        tickets -= seats
        rows.forEach { row ->
            columns.forEach { column ->
                hall[row][column] = 1
            }
        }
    }

    override fun toString(): String {
        return "Name: $name, Start: $start, " +
                "Duration: $duration, Rest of tickets: $tickets"
    }
}
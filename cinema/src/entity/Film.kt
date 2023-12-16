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
    private var hall: Hall = Hall()

    fun fillHall() {
        hall.fill()
    }

    fun getHall(): Hall {
        return hall
    }

    fun printHall(): String {
        return hall.toString()
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

    fun buyTickets(seats: MutableList<Pair<Int, Int>>) {
        tickets -= seats.size
        hall.buyTickets(seats)
    }

    override fun toString(): String {
        return "Name: $name, Start: $start, " +
                "Duration: $duration, Rest of tickets: $tickets"
    }
}
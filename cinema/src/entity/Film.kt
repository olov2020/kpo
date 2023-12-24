package entity

import source.SEATS

class Film(
    private val setName: String,
    private val setStart: Int,
    private val setDuration: Int,
    private val setTickets: Int,
    private val setHall: Hall
) {
    private var name: String = setName
    private var start: Int = setStart
    private var duration: Int = setDuration
    private var tickets: Int = setTickets
    private var hall: Hall = setHall

    fun reset() {
        fillHall()
        setTickets()
    }

    private fun fillHall() {
        hall = Hall()
        hall.fill()
    }

    fun getHall(): Hall {
        return hall
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

    private fun setTickets() {
        tickets = SEATS
    }

    fun setName(newName: String) {
        name = newName
    }

    fun setStart(newStart: Int) {
        start = newStart
    }

    fun setDuration(newDuration: Int) {
        duration = newDuration
    }

    fun buyTickets(seats: MutableList<Pair<Int, Int>>) {
        tickets -= seats.size
        hall.buyTickets(seats)
    }

    fun returnTickets(seats: MutableList<Pair<Int, Int>>) {
        tickets += seats.size
        hall.returnTickets(seats)
    }

    fun toFile(): String {
        return "Name: $name, Start: $start, " +
                "Duration: $duration, Rest of tickets: $tickets"
    }

    override fun toString(): String {
        return "Name: $name, Start: ${start / 60}:${start % 60}, " +
                "Duration: ${duration / 60}:${duration % 60}, Rest of tickets: $tickets"
    }
}
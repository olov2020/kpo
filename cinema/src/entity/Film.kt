package entity

import source.PATH_FILMS
import source.PATH_HALL
import source.SEATS
import java.io.File
import java.io.FileWriter

class Film(
    private val setName: String,
    private val setStart: Int,
    private val setDuration: Int,
    private val setTickets: Int,
    private val setHall: Hall
) {
    private val name: String = setName
    private val start: Int = setStart
    private val duration: Int = setDuration
    private var tickets: Int = setTickets
    private var hall: Hall = setHall

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

    fun toFile(): String {
        return "Name: $name, Start: $start, " +
                "Duration: $duration, Rest of tickets: $tickets"
    }

    override fun toString(): String {
        return "Name: $name, Start: ${start / 60}:${start % 60}, " +
                "Duration: ${duration / 60}:${duration % 60}, Rest of tickets: $tickets"
    }
}
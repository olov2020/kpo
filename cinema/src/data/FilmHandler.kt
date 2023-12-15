package data

import entity.Cinema
import entity.Film

fun inputSeats(tickets: Int): Int {
    println("Now you can choose your seat(s)")
    println("Input amount of seats you need")

    val seats: Int = readln().toInt()

    if (seats > tickets) {
        println("We have less tickets than seats you need")
        println("Enter number less of equal $tickets")
        inputSeats(tickets)
    } else if (seats <= 0) {
        println("Sorry, you can't buy 0 or less tickets")
        println("Enter number more than 0")
        inputSeats(tickets)
    }

    return seats
}

fun chooseFilm(cinema: Cinema, movieName: String) {
    val start: Int = cinema.getFilmsByName()[movieName]!!.getStart()
    var tickets: Int = cinema.getFilmsByName()[movieName]!!.getTickets()
    val duration: Int = cinema.getFilmsByName()[movieName]!!.getDuration()

    println("Great choice!")
    println("We have $tickets tickets on this film")
    println(
        "$movieName starts at ${start / 60}:${start % 60} " + "and lasts for ${duration / 60}:${duration % 60}"
    )

    cinema.getFilmsByName()[movieName]!!.buyTickets(inputSeats(tickets))
    tickets = cinema.getFilmsByName()[movieName]!!.getTickets()
}
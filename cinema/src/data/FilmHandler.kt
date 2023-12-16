package data

import entity.Cinema
import entity.Film
import entity.Hall
import source.COLUMNS
import source.ROWS

fun inputSeats(film: Film): MutableList<Pair<Int, Int>> {
    val hall: Hall = film.getHall()
    val tickets: Int = film.getTickets()
    var numOfSeats: Int

    while (true) {
        println("Now you can choose your seat(s)")
        println(hall)
        println("Enter amount of seats you want to buy")

        numOfSeats = readln().toInt()

        if (numOfSeats > tickets) {
            println("We have less tickets than seats you need")
            println("Enter number less of equal $tickets\n")
            continue
        } else if (numOfSeats <= 0) {
            println("Sorry, you can't buy 0 or less tickets")
            println("Enter number more than 0\n")
            continue
        } else {
            break
        }
    }
    println()

    var seatsToBuy: MutableList<Pair<Int, Int>> = mutableListOf()
    for (i in 0..<numOfSeats) {
        while (true) {
            println(
                "Enter number of row (starting from top) " +
                        "and number of column (starting from left) divided by space"
            )
            var arr: MutableList<Int> = mutableListOf()
            val input: String = readln()
            try {
                input.split(" ").forEach {
                    arr.add(it.toInt())
                }
                if (arr.size != 2) {
                    throw Exception()
                }
            } catch (e: Exception) {
                println("Oops, something has gone wrong\n")
                continue
            }
            if (arr[0] <= 0 || arr[0] > ROWS || arr[1] <= 0 || arr[1] > COLUMNS) {
                println("You entered wrong data for seats, try again\n")
                continue
            }
            if (hall.getHall()[arr[0] - 1][arr[1] - 1] == 'x') {
                println("Sorry, this sit has already been sold. Choose another one\n")
                continue
            }
            seatsToBuy.add(Pair(arr[0] - 1, arr[1] - 1))
            break
        }
        println()
    }
    println("Thanks for coming to us. You have just bought $numOfSeats seats. See you soon\n")

    return seatsToBuy
}

fun chooseFilm(film: Film) {
    val start: Int = film.getStart()
    var tickets: Int = film.getTickets()
    val duration: Int = film.getDuration()
    val name: String = film.getName()

    println("Great choice!")
    println("We have $tickets tickets on this film")
    println(
        "$name starts at ${start / 60}:${start % 60} " + "and lasts for ${duration / 60}:${duration % 60}"
    )

    film.buyTickets(inputSeats(film))
    film.printHall()
}
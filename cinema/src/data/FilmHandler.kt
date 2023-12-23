package data

import entity.Cinema
import entity.Film
import entity.Hall
import source.COLUMNS
import source.ROWS
import source.SEATS

fun addFilm() {
    while (true) {
        println("Do you want to add film?")
        val input: String = readln().lowercase()

        if (input == "yes") {
            break
        } else if (input == "no") {
            return
        }
    }
}

fun inputSeats(film: Film, choice: String): MutableList<Pair<Int, Int>> {
    val hall: Hall = film.getHall()
    val tickets: Int = film.getTickets()
    var numOfSeats: Int

    while (true) {
        println("Now you can choose your seat(s)")
        println(hall)
        println("Enter amount of seats you want to $choice")

        val seatsString = readln()

        try {
            numOfSeats = seatsString.toInt()
        } catch (e: Exception) {
            println("Oops, something has gone wrong, try again")
            continue
        }

        if (numOfSeats > tickets && choice == "buy") {
            println("We have less tickets than seats you need to $choice")
            println("Enter number less of equal $tickets\n")
            continue
        } else if (numOfSeats > SEATS - tickets && choice == "return") {
            println("We have less tickets than seats you need to $choice")
            println("Enter number less of equal ${SEATS - tickets}\n")
            continue
        } else if (numOfSeats <= 0) {
            println("Sorry, you can't $choice 0 or less tickets")
            println("Enter number more than 0\n")
            continue
        } else {
            break
        }
    }
    println()

    return buyOrReturnSeats(hall, numOfSeats, choice)
}

fun checkSeats(hall: Hall, choice: String): MutableList<Int> {
    var arr: MutableList<Int> = mutableListOf()

    while (true) {
        println(
            "Enter number of row (starting from top) " +
                    "and number of column (starting from left) divided by space"
        )
        val input: String = readln()
        try {
            arr.clear()
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

        if (choice == "buy")
        {
            if (hall.getHall()[arr[0] - 1][arr[1] - 1] == "x") {
                println("Sorry, this sit has already been sold. Choose another one\n")
            } else {
                break
            }
        }

        if (choice == "return")
        {
            if (hall.getHall()[arr[0] - 1][arr[1] - 1] == "o") {
                println("Sorry, this sit hasn't been sold. Choose another one\n")
            } else {
                break
            }
        }
    }

    return arr
}

fun buyOrReturnSeats(hall: Hall, numOfSeats: Int, choice: String): MutableList<Pair<Int, Int>> {
    var seats: MutableList<Pair<Int, Int>> = mutableListOf()
    for (i in 0..<numOfSeats) {
        val arr: MutableList<Int> = checkSeats(hall, choice)
        seats.add(Pair(arr[0] - 1, arr[1] - 1))

        println()
    }
    println("Thanks for coming to us. You have just $choice $numOfSeats seats. See you soon\n")

    return seats
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

    var choice: String
    while (true) {
        println("Enter <buy> to buy tickets")
        println("Enter <return> to buy tickets")
        choice = readln().lowercase()

        if (choice == "buy" || choice == "return") {
            break
        } else {
            println("Oops, something has gone wrong, try again\n")
        }
    }
    println()

    if (choice == "buy") {
        film.buyTickets(inputSeats(film, choice))
    } else {
        film.returnTickets(inputSeats(film, choice))
    }
}
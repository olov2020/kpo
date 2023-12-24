package data

import entity.Cinema
import entity.Film
import entity.Hall
import source.*

fun inputFilm(cinema: Cinema): Film {
    while (true) {
        println("Enter name of the film below to change info")
        cinema.show()

        val input: String = readln()

        if (input in cinema.getFilmsByName().keys) {
            println()
            return cinema.getFilm(input)
        } else {
            println("Enter valid name of the film\n")
        }
    }
}

fun setName(cinema: Cinema): String {
    while (true) {
        println("Input new name")
        val name: String = readln()

        if (name in cinema.getFilmsByName().keys) {
            println("Film with this name already exists, choose another one\n")
            continue
        }

        return name
    }
}

fun setStartOrDuration(choice: String): Int {
    while (true) {
        println("Input new $choice time of the film")
        println("(2 integers divided by space)")
        val start: String = readln()
        val arr: MutableList<Int> = mutableListOf()

        try {
            start.split(" ").forEach {
                arr.add(it.toInt())
            }
            if (arr.size != 2) {
                throw Exception("Oops, something has gone wrong\n")
            }
        } catch (e: Exception) {
            println(e.message)
            continue
        }
        return arr[0] * 60 + arr[1]
    }
}

fun changeInfo(cinema: Cinema) {
    val film: Film = inputFilm(cinema)

    while (true) {
        println("What do you want to change?")
        println(film)
        println("Enter <name>, <start>, <duration> or <nothing>")

        val input = readln().lowercase()
        println()

        if (input == "name") {
            val name: String = setName(cinema)
            film.setName(name)
            cinema.toFileFilms()
            println("Name has been changed successfully\n")
        } else if (input == "start") {
            val start: Int = setStartOrDuration("start")
            film.setStart(start)
            cinema.toFileFilms()
            println("Start has been changed successfully\n")
        } else if (input == "duration") {
            val duration: Int = setStartOrDuration("duration")
            film.setDuration(duration)
            cinema.toFileFilms()
            println("Start has been changed successfully\n")
        } else if (input == "nothing") {
            return
        } else {
            println("Oops, something has gone wrong, try again\n")
        }
    }
}

fun addFilm(cinema: Cinema) {
    val name: String = setName(cinema)
    val start: Int = setStartOrDuration("start")
    val duration: Int = setStartOrDuration("duration")
    val hall = Hall()
    hall.fill()
    val film = Film(name, start, duration, SEATS, hall)
    cinema.addFilm(film)
    println("Film was added successfully\n")
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
            println("Oops, something has gone wrong, try again\n")
            continue
        }

        if (numOfSeats > tickets && choice == "buy") {
            println("We have less tickets than seats you need to $choice")
            println("Enter number less of equal $tickets\n")
        } else if (numOfSeats > SEATS - tickets && choice == "return") {
            println("We have less tickets than seats you need to $choice")
            println("Enter number less of equal ${SEATS - tickets}\n")
        } else if (numOfSeats <= 0) {
            println("Sorry, you can't $choice 0 or less tickets")
            println("Enter number more than 0\n")
        } else {
            break
        }
    }
    println()

    return buyOrReturnSeats(hall, numOfSeats, choice)
}

fun checkSeats(hall: Hall, choice: String): MutableList<Int> {
    val arr: MutableList<Int> = mutableListOf()

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

        if (choice == "buy") {
            if (hall.getHall()[arr[0] - 1][arr[1] - 1] == "x") {
                println("Sorry, this sit has already been sold. Choose another one\n")
            } else {
                break
            }
        }

        if (choice == "return") {
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
    val seats: MutableList<Pair<Int, Int>> = mutableListOf()
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
    val tickets: Int = film.getTickets()
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
        if (film.getStart() <= TIME_TO_RETURN) {
            println("Sorry, you can't return tickets on this film. It has already started...\n")
            return
        }

        if (film.getTickets() == SEATS) {
            println("Sorry, you can't return tickets on this film. None of tickets was sold\n")
            return
        }
        film.returnTickets(inputSeats(film, choice))
    }
}
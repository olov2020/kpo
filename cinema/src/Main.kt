import data.*
import entity.Cinema

fun user(cinema: Cinema) {
    cinema.loadFilms()

    while (true) {
        println("Welcome to the cinema!\nWhat film are you interested in? Choose below")
        cinema.show()
        println("Enter 0 to quit")
        val input = readln()
        println()

        if (input == "0") {
            break
        } else if (input in cinema.getFilmsByName().keys) {
            chooseFilm(cinema.getFilm(input))
        } else {
            println("Oops, something has gone wrong, try again\n")
        }
    }

}

fun worker(cinema: Cinema) {
    manageAccount()
    cinema.loadFilms()

    while (true) {
        println("Choose function below")
        println("Enter 1 to add new film")
        println("Enter 2 to change information about film")
        println("Enter 0 to quit")
        println("Enter <reset> to reset all tickets for all films")

        val input: String = readln()
        println()

        if (input == "0") {
            break
        } else if (input == "1") {
            addFilm(cinema)
        } else if (input == "2") {
            changeInfo(cinema)
        } else if (input == "reset") {
            cinema.reset()
            println("Reset has been done successfully\n")
        } else {
            println("Oops, something has gone wrong, try again\n")
        }
    }
}

fun main() {/*var cinema: Cinema = Cinema()
    val hall: Hall = Hall()
    hall.fill()
    val film1: Film = Film("e", 13 * 60 + 30, 10, 25, hall)
    cinema.addFilm(film1)
    return*/

    while (true) {
        val cinema = Cinema()

        val userType: String = handleAccount()

        if (userType == "user") {
            user(cinema)
        } else if (userType == "worker") {
            worker(cinema)
        } else if (userType == "exit") {
            break
        } else {
            println("Oops, something has gone wrong, try again")
        }

        cinema.toFileFilms()
        println("Thanks, bye\n")
    }

    println("Program finished successfully")
}
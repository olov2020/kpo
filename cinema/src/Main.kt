import data.*
import entity.Cinema
import entity.Account
import entity.Film
import entity.Hall

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

    while (true) {
        println("Choose function below")
        println("Enter 1 to add new film")
        println("Enter 2 to change information about film")
        println("Enter 0 to quit")

        val input: String = readln()

        if (input == "0") {
            break
        } else if (input == "1") {
            addFilm()
        }
    }
}

fun main() {
    /*var cinema: Cinema = Cinema()
    val hall: Hall = Hall()
    hall.fill()
    val film1: Film = Film("a", 123, 10, 25, hall)
    val film2: Film = Film("b", 10, 12, 25, hall)
    val film3: Film = Film("c", 150, 11, 25, hall)
    val film4: Film = Film("d", 97, 6, 25, hall)
    cinema.addFilm(film1)
    cinema.addFilm(film2)
    cinema.addFilm(film3)
    cinema.addFilm(film4)
    cinema.loadFilms()
    cinema.show()
    return*/

    var cinema: Cinema = Cinema()

    val userType: String = handleAccount()
    if (userType == "user") {
        user(cinema)
    } else if (userType == "worker") {
        worker(cinema)
    } else if (userType == "reset") {
        cinema.loadFilms()
        cinema.reset()
    } else {
        println("Oops, something has gone wrong, try again")
    }

    cinema.toFileFilms()
    println("Thanks, bye")
}
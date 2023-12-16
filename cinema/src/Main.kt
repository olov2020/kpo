import data.manageAccount
import entity.Cinema
import data.chooseFilm
import data.returnTickets
import entity.Account

fun user() {
    val cinema = Cinema()
    cinema.loadFilms()

    while (true) {
        println("Welcome to the cinema!\nWhat film do your want to see? Choose below")
        cinema.show()
        println("If you want to return tickets, enter 1")
        println("To quit enter 0")
        val input = readln()
        println()

        if (input == "0") {
            break
        } else if (input == "1") {
            returnTickets(cinema)
        } else if (input in cinema.getFilmsByName().keys) {
            chooseFilm(cinema.getFilm(input))
        } else {
            println("Oops, something has gone wrong, try again\n")
        }
    }

    println("Thanks, bye!")

    cinema.toFileFilms()
}

fun worker() {

}

fun main() {
    /*val account1: Account = Account("a", "something")
    account1.archive()
    val account2: Account = Account("b", "hithere")
    account2.archive()
    val account3: Account = Account("c", "bbb")
    account3.archive()
    val account4: Account = Account("Mike", "12345678")
    account4.archive()*/

    val userType: String = manageAccount()
    if (userType == "user") {
        user()
    } else if (userType == "worker") {
        worker()
    } else {
        println("Oops, something has gone wrong, try again")
    }
}
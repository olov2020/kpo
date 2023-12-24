package data

import entity.Account
import java.io.File
import source.PATH_ACCOUNTS
import source.PASSWORD
import source.xor

fun readFromFile(): MutableMap<String, String> {
    val accounts: MutableMap<String, String> = mutableMapOf()

    var allStrings = ""
    File(PATH_ACCOUNTS).useLines { lines ->
        lines.forEach {
            allStrings += it
            if (it[it.length - 1] != ';') {
                allStrings += "\r"
            }
        }
    }

    val arr: MutableList<String> = allStrings.split(";").toMutableList()
    for (i in 0..arr.size - 2 step 2) {
        accounts[arr[i]] = arr[i + 1]
    }
    println()

    /*accounts.forEach {
        println(">  ${it.key} = ${it.value xor PASSWORD}")
    }*/

    return accounts
}

fun logIn() {
    val accounts: MutableMap<String, String> = readFromFile()

    val username: String
    while (true) {
        print("Enter username: ")
        val input: String = readln()
        if (input in accounts.keys) {
            username = input
            break
        } else {
            println("Sorry, wrong username\n")
        }
    }

    while (true) {
        print("Enter password: ")
        val input: String = readln()
        if (input xor PASSWORD == accounts[username]) {
            println("Successful entry\n")
            return
        } else {
            println("Sorry, wrong password\n")
        }
    }
}

fun register() {
    val accounts: MutableMap<String, String> = readFromFile()

    var name: String
    var password: String
    while (true) {
        println("Input your nickname")
        name = readln()

        if (name in accounts.keys) {
            println("Account with this name already exists\n")
            manageAccount()
            return
        }

        println("Input your password")
        password = readln()

        var input: String
        while (true) {
            println("\nYour nickname is $name, password is $password")
            println("Is this data correct?")
            input = readln().lowercase()
            if (input == "yes") {
                println("Account was created successfully")
                break
            } else if (input == "no") {
                println("Then input correct data again\n")
                break
            } else {
                println("Oops, something has gone wrong, try again\n")
            }
        }
        if (input == "yes") {
            break
        }
    }

    val account = Account(name, password)
    account.archive()
    logIn()
}

fun manageAccount() {
    while (true) {
        println("Do you have an account?")
        val input: String = readln().lowercase()

        if (input == "yes") {
            logIn()
            return
        } else if (input == "no") {
            register()
            return
        } else {
            println("Oops, something has gone wrong, try again\n")
        }
    }
}

fun handleAccount(): String {
    while (true) {
        println("Hi, are you worker?")
        println("Enter <exit> to quit the program")
        val input: String = readln().lowercase()

        if (input == "yes") {
            println()
            return "worker"
        } else if (input == "no") {
            println()
            return "user"
        } else if (input == "exit") {
            println()
            return "exit"
        } else {
            println("Oops, something has gone wrong, try again")
        }
        println()
    }
}

package data

import java.io.File
import source.PATH_ACCOUNTS
import source.PASSWORD
import source.xor

fun readFromFile(): MutableMap<String, String> {
    val accounts: MutableMap<String, String> = mutableMapOf<String, String>()

    var allStrings: String = ""
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

    accounts.forEach {
        println(">  ${it.key} = ${it.value xor PASSWORD}")
    }

    return accounts
}

fun manageAccount():String {
    while (true) {
        println("Hi, are you worker?")
        val input: String = readln().lowercase()

        if (input == "yes") {
            println()
            break
        } else if (input == "no") {
            println()
            return "user"
        } else {
            println("Oops, something has gone wrong, try again")
        }
        println()
    }

    val accounts: MutableMap<String, String> = readFromFile()

    val username: String
    while (true) {
        print("Enter username: ")
        val input: String = readln()
        if (input in accounts.keys) {
            username = input
            println()
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
            break
        } else {
            println("Sorry, wrong password\n")
        }
    }

    return "worker"
}

class Account(val name_: String, val id_: Int) {
    var money = 0
    var name = name_
    var id = id_

    override fun toString(): String = "Name: " + name + ", ID: " + id.toString() + ", Money: " + money.toString()
}

class Bank() {
    var accounts: MutableList<Account> = mutableListOf()

    fun createAccount(name: String, id: Int) {
        accounts.add(Account(name, id))
    }
}

fun main() {
    var cnt: Int = 0
    var b = Bank()
    while (true) {
        println("Hi there, enter number from 0 to 4 to choose function:")
        println("Enter 0 to exit")
        println("Enter 1 to look at the list of accounts")
        println("Enter 2 to open new account")
        println("Enter 3 to refill account")
        println("Enter 4 to swap money between accounts")
        val choice: Int = readLine()!!.toInt()
        println()

        if (choice == 0) {
            break
        } else {
            if (choice == 1) {
                if (b.accounts.size == 0) {
                    println("There are no open accounts")
                } else {
                    println("All accounts")
                    for (item in b.accounts) {
                        println(item.toString())
                    }
                }
                println()
            }

            if (choice == 2) {
                println("Enter name for account")
                val name: String = readLine().toString()
                println()
                b.createAccount(name, cnt++)
            }

            if (choice == 3) {
                println("Enter id of account")
                val id: Int = readLine()!!.toInt()
                println("Enter amount of money to put")
                val money: Int = readLine()!!.toInt()
                if (id < cnt) {
                    b.accounts[id].money += money
                } else {
                    println("wrong id input")
                }
                println()
            }

            if (choice == 4) {
                println("Enter id of account from which to take money")
                val id_from: Int = readLine()!!.toInt()
                println("Enter id of account where to put money")
                val id_to: Int = readLine()!!.toInt()
                println("Enter amount of money to move")
                val money: Int = readLine()!!.toInt()
                if (id_from >= cnt || id_to >= cnt) {
                    println("wrong id input")
                } else if (b.accounts[id_from].money < money) {
                    println("There is no such amount of money to move")
                } else {
                    b.accounts[id_to].money += money
                    b.accounts[id_from].money -= money
                }
                println()
            }
        }
    }

    println("Thanks for using program, bye")
}
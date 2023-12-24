package entity

import source.PATH_ACCOUNTS
import source.PASSWORD
import source.xor
import java.io.FileWriter

class Account(
    private val setName: String,
    private val setPassword: String,
) {
    private val name: String = setName
    private val password: String = setPassword

    fun archive() {
        FileWriter(PATH_ACCOUNTS, true).use {
            it.write(this.toString() + "\n")
        }
    }

    override fun toString(): String {
        val lockedPassword: String = password xor PASSWORD
        return "$name;$lockedPassword;"
    }
}
package ru.hse.restaurant.autherization

import ru.hse.restaurant.model.Administrator
import ru.hse.restaurant.model.Customer
import org.mindrot.jbcrypt.BCrypt

class UserData(
    val admins: MutableList<Administrator> = mutableListOf(),
    val customers: MutableList<Customer> = mutableListOf(),
) {
    fun addAdmin(admin: Administrator) {
        admins.add(admin)
    }

    fun addCustomer(customer: Customer) {
        customers.add(customer)
    }

    private fun getAdminByLogin(login: String): Administrator? {
        return admins.find { it.getLogin() == login }
    }

    private fun getCustomerByLogin(login: String): Customer? {
        return customers.find { it.getLogin() == login }
    }

    fun checkUser(login: String): Boolean {
        return getAdminByLogin(login) != null || getCustomerByLogin(login) != null
    }

    fun validateCustomerPassword(login: String, password: String): Boolean {
        val user = getCustomerByLogin(login)
        return user != null && BCrypt.checkpw(password, user.getHashedPassword())
    }

    fun validateAdminPassword(login: String, password: String): Boolean {
        val user = getAdminByLogin(login)
        return user != null && BCrypt.checkpw(password, user.getHashedPassword())
    }
}
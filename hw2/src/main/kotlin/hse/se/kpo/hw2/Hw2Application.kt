@file:Suppress("DEPRECATION")

package hse.se.kpo.hw2

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HelloController {

    @GetMapping("/hello-all")
    fun helloAll(): String {
        return "Hello to all users!"
    }

    @GetMapping("/hello-user")
    @PreAuthorize("hasRole('USER')")
    fun helloUser(): String {
        return "Hello to user!"
    }

    @GetMapping("/hello-admin")
    @PreAuthorize("hasRole('ADMIN')")
    fun helloAdmin(): String {
        return "Hello to admin!"
    }
}

@Suppress("DEPRECATION")
@EnableGlobalMethodSecurity(prePostEnabled = true)
@SpringBootApplication
class Application

fun main(args: Array<String>) {
    runApplication<Application>(*args)
}
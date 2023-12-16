package entity

import source.PATH_ACCOUNTS
import source.PATH_FILMS
import java.io.File
import java.io.FileWriter

class Cinema {
    private var films: MutableList<Film> = mutableListOf()

    fun show() {
        for (elem in films) {
            println(elem)
        }
    }

    fun getFilms(): MutableList<Film> {
        return films
    }

    fun getFilm(movieName: String): Film {
        films.forEach {
            if (it.getName() == movieName) {
                return it
            }
        }
        throw Exception("No such film")
    }

    fun getFilmsByName(): MutableMap<String, Film> {
        val newFilms = mutableMapOf<String, Film>()
        films.forEach {
            newFilms[it.getName()] = it
        }
        return newFilms
    }

    fun addFilm(vararg newFilm: Film) {
        FileWriter(PATH_FILMS, true).use {
            newFilm.forEach { film ->
                it.write(film.toString() + "\n")
            }
        }
    }

    fun loadFilms() {
        File(PATH_FILMS).useLines { lines ->
            lines.forEach { line ->
                val arr: MutableList<String> = line.split(", ", ": ").toMutableList()
                var film: Film = Film(arr[1], arr[3].toInt(), arr[5].toInt(), arr[7].toInt())
                film.fillHall()
                films.add(film)
            }
        }
    }

    fun checkTickets(film: Film): Int {
        films.forEach {
            if (it.getName() == film.getName()) {
                return it.getTickets()
            }
        }
        return 0
    }
}
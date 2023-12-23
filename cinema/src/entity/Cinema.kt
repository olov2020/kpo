package entity

import source.PATH_ACCOUNTS
import source.PATH_FILMS
import source.PATH_HALL
import java.io.File
import java.io.FileWriter

class Cinema {
    private var films: MutableList<Film> = mutableListOf()

    fun reset() {
        films.forEach {
            it.reset()
        }
        toFileFilms()
    }

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
                it.write(film.toFile() + "\n")
                toFileHall(film)
            }
        }
    }

    fun toFileFilms() {
        File(PATH_HALL).delete()
        File(PATH_FILMS).printWriter().use {
            films.forEach { film ->
                it.println(film.toFile())
                toFileHall(film)
            }
        }
    }

    fun toFileHall(film: Film) {
        FileWriter(PATH_HALL, true).use {
            it.write(film.getHall().toFile() + "\n")
        }
    }

    fun loadFilmsHall(cnt: Int): Hall {
        var hall: Hall = Hall()
        var list: MutableList<MutableList<String>> = mutableListOf()
        var linesList: MutableList<String> = mutableListOf()

        File(PATH_HALL).useLines { lines ->
            linesList = lines.toMutableList()
        }

        var line: String = ""
        try {
            line = linesList[cnt]
        } catch (e: Exception) {
            return hall
        }
        var arr: MutableList<String> = line.split(";").toMutableList()
        arr = arr.subList(0, arr.size - 1)
        arr.forEach {
            var stringList: MutableList<String> = it.split(",").toMutableList()
            stringList = stringList.subList(0, stringList.size - 1)
            list.add(stringList)
        }

        hall.setHall(list)
        // println(hall)
        return hall
    }

    fun loadFilms() {
        var cnt: Int = 0
        File(PATH_FILMS).useLines { lines ->
            lines.forEach { line ->
                val arr: MutableList<String> = line.split(", ", ": ").toMutableList()
                val hall: Hall = loadFilmsHall(cnt)
                var film: Film = Film(arr[1], arr[3].toInt(), arr[5].toInt(), arr[7].toInt(), hall)
                //film.fillHall()
                ++cnt
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
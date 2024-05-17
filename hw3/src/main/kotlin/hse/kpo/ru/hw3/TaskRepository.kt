package hse.kpo.ru.hw3

interface TaskRepository {

    fun findAll(): List<Task>

    fun createTask(title: String, desc: String): Task
}

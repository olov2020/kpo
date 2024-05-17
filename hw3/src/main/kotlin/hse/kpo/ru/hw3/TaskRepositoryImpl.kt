package hse.kpo.ru.hw3

import org.springframework.stereotype.Repository
import java.util.concurrent.ConcurrentHashMap

@Repository
class TaskRepositoryImpl: TaskRepository {

    private val todos = ConcurrentHashMap<Int, Task>()

    override fun findAll(): List<Task> {
        return todos.values.toList()
    }

    override fun createTask(title: String, desc: String): Task {
        val task = Task(todos.size + 1, title, desc)
        todos[task.id] = task
        return task
    }
}
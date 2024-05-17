package hse.kpo.ru.hw3

import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.MutationMapping
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.scheduling.config.Task
import org.springframework.stereotype.Controller

@Controller
class TaskController(private val taskService: TaskService) {

    @QueryMapping
    fun todos(): List<hse.kpo.ru.hw3.Task> {
        return taskService.findAll()
    }

    @MutationMapping
    fun createTask(@Argument title: String, @Argument desc: String): hse.kpo.ru.hw3.Task {
        return taskService.createTask(title, desc)
    }
}

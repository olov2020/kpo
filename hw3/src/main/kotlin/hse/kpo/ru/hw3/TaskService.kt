package hse.kpo.ru.hw3

import org.springframework.scheduling.config.Task
import org.springframework.stereotype.Service

@Service
class TaskService(val taskRepository: TaskRepository) {

    fun findAll(): List<hse.kpo.ru.hw3.Task> {
        return taskRepository.findAll()
    }

    fun createTask(title: String, desc: String): hse.kpo.ru.hw3.Task {
        return taskRepository.createTask(title, desc)
    }
}


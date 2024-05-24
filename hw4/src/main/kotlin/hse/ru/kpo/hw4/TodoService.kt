package hse.ru.kpo.hw4

import org.springframework.stereotype.Service

@Service
class TodoService(private val todoRepository: TodoRepository) {
    fun getAllTodos(): List<Todo> {
        return todoRepository.findAll()
    }

    fun createTodo(todo: Todo): Todo {
        return todoRepository.save(todo)
    }

    fun deleteTodoById(id: Long) {
        todoRepository.deleteById(id)
    }
}
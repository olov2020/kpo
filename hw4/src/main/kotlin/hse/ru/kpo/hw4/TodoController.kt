package hse.ru.kpo.hw4

import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/todos")
class TodoController(private val todoService: TodoService) {
    @GetMapping
    fun getAllTodos(): List<Todo> {
        return todoService.getAllTodos()
    }

    @PostMapping
    fun createTodo(@RequestBody todo: Todo): Todo {
        return todoService.createTodo(todo)
    }

    @DeleteMapping("/{id}")
    fun deleteTodoById(@PathVariable id: Long) {
        todoService.deleteTodoById(id)
    }
}
package hse.ru.kpo.hw4

import org.junit.Assert
import org.junit.Test
import org.junit.jupiter.api.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import sun.jvm.hotspot.utilities.Assert

@RunWith(MockitoJUnitRunner::class)
class TodoServiceTest {

    @Mock
    private lateinit var todoRepository: TodoRepository

    @InjectMocks
    private lateinit var todoService: TodoService

    @Test
    fun testGetAllTodos() {
        val todos = listOf(
            Todo(1L, "Задача 1", false),
            Todo(2L, "Задача 2", true)
        )

        Mockito.`when`(todoRepository.findAll()).thenReturn(todos)

        val result = todoService.getAllTodos()

        Assert.assertEquals(2, result.size)
        Assert.assertEquals("Задача 1", result[0].title)
        Assert.assertEquals("Задача 2", result[1].title)
    }
}
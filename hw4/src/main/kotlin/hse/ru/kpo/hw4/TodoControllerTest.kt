package hse.ru.kpo.hw4

import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import java.nio.file.Paths.get

@RunWith(SpringRunner::class)
@WebMvcTest(TodoController::class)
class TodoControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @MockBean
    private lateinit var todoService: TodoService

    @Test
    fun testGetAllTodos() {
        val todos = listOf(
            Todo(1L, "Задача 1", false),
            Todo(2L, "Задача 2", true)
        )

        Mockito.`when`(todoService.getAllTodos()).thenReturn(todos)

        mockMvc.perform(get("/todos"))
            .andExpect(status().isOk)
            .andExpect(jsonPath("$", hasSize<Int>(2)))
            .andExpect(jsonPath("$[0].title", `is`("Задача 1")))
            .andExpect(jsonPath("$[1].title", `is`("Задача 2")))
    }
}
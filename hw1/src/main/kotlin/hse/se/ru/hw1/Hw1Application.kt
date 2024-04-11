import hse.se.ru.hw1.Application
import org.springframework.boot.SpringApplication
import org.springframework.context.annotation.*
import org.springframework.core.type.AnnotatedTypeMetadata
import org.springframework.stereotype.Component


// Класс, представляющий условие для бина
class WindowsCondition : Condition {
    override fun matches(context: ConditionContext, metadata: AnnotatedTypeMetadata): Boolean {
        return System.getProperty("os.name").contains("Windows", ignoreCase = true)
    }
}

// Простой компонент
@Component
class SimpleComponent

// Конфигурационный класс для создания бина с использованием @Bean
@Configuration
class AppConfig {
    @Bean
    fun nonSingletonBean(): SimpleComponent {
        return SimpleComponent()
    }
}

// Использование @Conditional для создания бина в зависимости от условия
@Configuration
class ConditionalConfig {
    @Bean
    @Conditional(WindowsCondition::class)
    fun conditionalBean(): SimpleComponent {
        return SimpleComponent()
    }
}


fun main(args: Array<String>) {
    SpringApplication.run(Application::class.java, *args)
}


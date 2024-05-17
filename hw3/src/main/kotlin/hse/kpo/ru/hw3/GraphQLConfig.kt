package hse.kpo.ru.hw3

import graphql.GraphQL
import graphql.schema.idl.RuntimeWiring
import graphql.schema.idl.SchemaGenerator
import graphql.schema.idl.SchemaParser
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.io.File

@Configuration
class GraphQLConfig {

    @Bean
    fun graphQL(taskController: TaskController): GraphQL {
        val schemaFile = File("schema.graphqls")
        val schemaParser = SchemaParser()
        val typeRegistry = schemaParser.parse(schemaFile)
        val runtimeWiring = buildRuntimeWiring(taskController)
        val schemaGenerator = SchemaGenerator()
        val graphQLSchema = schemaGenerator.makeExecutableSchema(typeRegistry, runtimeWiring)
        return GraphQL.newGraphQL(graphQLSchema).build()
    }

    private fun buildRuntimeWiring(taskController: TaskController): RuntimeWiring {
        return RuntimeWiring.newRuntimeWiring()
            .type("Query") { builder ->
                builder.dataFetcher("todos") { env -> taskController.todos() }
            }
            .type("Mutation") { builder ->
                builder.dataFetcher("createTask") { env ->
                    taskController.createTask(
                        env.getArgument("title"),
                        env.getArgument("desc")
                    )
                }
            }
            .build()
    }
}

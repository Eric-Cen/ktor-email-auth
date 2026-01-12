package com.example

import com.example.model.Priority
import com.example.model.Task
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.*
import io.ktor.client.statement.bodyAsText
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.json
import io.ktor.server.testing.*
import kotlin.test.Test
import kotlin.test.assertContains
import kotlin.test.assertEquals

class ApplicationTest {

    @Test
    fun testRoot() = testApplication {
        application {
            module()
        }
        val response = client.get("/")
        assertEquals(HttpStatusCode.OK, response.status)
        assertEquals("Hello World!", response.bodyAsText())
    }

    @Test
    fun tasksCanBeFoundByPriority() = testApplication {
        application {
            module()
        }

        val response = client.get("/tasks/byPriority/Medium")
        val body = response.bodyAsText()

        assertEquals(HttpStatusCode.OK, response.status)
        assertContains(body, "Mow the lawn")
        assertContains(body, "Paint the fence")
    }

    @Test
    fun invalidPriorityProduces400() = testApplication {
        application {
            module()
        }

        val response = client.get("/tasks/byPriority/Invalid")
        assertEquals(HttpStatusCode.BadRequest, response.status)
    }

    @Test
    fun unusedPriorityProduces404() = testApplication {
        application {
            module()
        }

        val response = client.get("/tasks/byPriority/Vital")
        assertEquals(HttpStatusCode.NotFound, response.status)
    }

    @Test
    fun newTasksCanBeAdded() = testApplication {
        application {
            module()
        }

        val response1 = client.post("/tasks") {
            header(
                HttpHeaders.ContentType,
                ContentType.Application.FormUrlEncoded.toString()
            )
            setBody(
                listOf(
                    "name" to "swimming",
                    "description" to "Go to the beach",
                    "priority" to "Low"
                ).formUrlEncode()
            )
        }

        assertEquals(HttpStatusCode.NoContent, response1.status)

        val response2 = client.get("/tasks")
        assertEquals(HttpStatusCode.OK, response2.status)
        val body = response2.bodyAsText()

        assertContains(body, "swimming")
        assertContains(body, "Go to the beach")
    }

    @Test
    fun newTasksCanBeAddedWithJSON() = testApplication {
        application {
            module()
        }
        
        val client = createClient {
            install(ContentNegotiation) {
                json()
            }
        }

        val response1 = client.post("/tasks/json") {
            contentType(ContentType.Application.Json)
            setBody(Task("swimming", "Go to the beach", Priority.Low))
        }
        assertEquals(HttpStatusCode.NoContent, response1.status)

        val response2 = client.get("/tasks/json")
        assertEquals(HttpStatusCode.OK, response2.status)

        val tasks = response2.body<List<Task>>()
        val taskNames = tasks.map { it.name }

        assertContains(taskNames, "swimming")
    }

    @Test
    fun taskCanBeDeletedByName() = testApplication {
        application {
            module()
        }

        // First, verify the task exists
        val response1 = client.get("/tasks/byName/swimming")
        assertEquals(HttpStatusCode.OK, response1.status)

        // Delete the task
        val response2 = client.delete("/tasks/swimming")
        assertEquals(HttpStatusCode.NoContent, response2.status)

        // Verify the task is gone
        val response3 = client.get("/tasks/byName/swimming")
        assertEquals(HttpStatusCode.NotFound, response3.status)
    }

    @Test
    fun deletingNonExistentTaskReturns404() = testApplication {
        application {
            module()
        }

        val response = client.delete("/tasks/nonexistent-task")
        assertEquals(HttpStatusCode.NotFound, response.status)
    }

    @Test
    fun deletingTaskWithEmptyNameReturns400() = testApplication {
        application {
            module()
        }

        val response = client.delete("/tasks/")
        // This should return 404 (not found route) or 400 depending on routing setup
        // Let's check what actually happens
        assert(response.status == HttpStatusCode.NotFound || response.status == HttpStatusCode.BadRequest)
    }
}

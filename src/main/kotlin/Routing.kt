package com.example

import com.example.com.example.models.Task
import com.example.com.example.repository.TaskRepository
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import java.util.*


fun Application.configureRouting() {
    routing {
        // ✅ Endpoint raíz para pasar el test
        get("/") {
            call.respondText("Hello, world!")
        }

        // ✅ CRUD de tareas
        route("/tasks") {

            get {
                call.respond(TaskRepository.getAllTasks())
            }

            get("/{id}") {
                val id = call.parameters["id"]
                val task = id?.let { TaskRepository.getTaskById(it) }

                if (task != null) {
                    call.respond(task)
                } else {
                    call.respond(HttpStatusCode.NotFound, "Tarea no encontrada")
                }
            }

            post {
                val taskRequest = call.receive<Task>()
                val newTask = taskRequest.copy(id = UUID.randomUUID().toString())
                TaskRepository.addTask(newTask)
                call.respond(HttpStatusCode.Created, newTask)
            }

            put("/{id}") {
                val id = call.parameters["id"]
                val taskRequest = call.receive<Task>()
                val updated = id?.let { TaskRepository.updateTask(it, taskRequest) } ?: false

                if (updated) {
                    call.respond(HttpStatusCode.OK, "Tarea actualizada")
                } else {
                    call.respond(HttpStatusCode.NotFound, "Tarea no encontrada")
                }
            }

            delete("/{id}") {
                val id = call.parameters["id"]
                val removed = id?.let { TaskRepository.deleteTask(it) } ?: false

                if (removed) {
                    call.respond(HttpStatusCode.OK, "Tarea eliminada")
                } else {
                    call.respond(HttpStatusCode.NotFound, "Tarea no encontrada")
                }
            }
        }
    }
}
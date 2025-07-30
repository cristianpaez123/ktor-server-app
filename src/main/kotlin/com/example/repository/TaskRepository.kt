package com.example.com.example.repository

import com.example.com.example.models.Task

object TaskRepository {
    private val tasks = mutableListOf<Task>()

    fun getAllTasks(): List<Task> = tasks

    fun getTaskById(id: String): Task? = tasks.find { it.id == id }

    fun addTask(task: Task) {
        tasks.add(task)
    }

    fun updateTask(id: String, updatedTask: Task): Boolean {
        val index = tasks.indexOfFirst { it.id == id }
        return if (index != -1) {
            tasks[index] = updatedTask.copy(id = id) // conservar el mismo ID
            true
        } else {
            false
        }
    }

    fun deleteTask(id: String): Boolean {
        return tasks.removeIf { it.id == id }
    }

    fun clearTasks() {
        tasks.clear()
    }
}

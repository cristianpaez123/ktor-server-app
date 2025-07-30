package com.example.com.example.models

import kotlinx.serialization.Serializable
import java.util.*

@Serializable
data class Task(
    val id: String = UUID.randomUUID().toString(),
    val title: String,
    val description: String,
    val isCompleted: Boolean = false
)
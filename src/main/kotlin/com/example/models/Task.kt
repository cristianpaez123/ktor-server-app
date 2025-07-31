package com.example.com.example.models

import kotlinx.serialization.Serializable
import java.util.*

@Serializable
data class Task(
    val id: String ,
    val title: String,
    val description: String,
    val isCompleted: Boolean = false
)
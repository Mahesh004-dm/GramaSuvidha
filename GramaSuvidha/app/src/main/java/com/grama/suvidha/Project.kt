package com.grama.suvidha

data class Project(
    val id: String = "",
    val name: String = "",
    val type: String = "",
    val location: String = "",
    val status: String = "",
    val progress: Int = 0,
    val description: String = "",
    val budget: String = ""
)
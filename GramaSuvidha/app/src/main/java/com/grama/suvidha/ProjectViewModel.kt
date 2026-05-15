package com.grama.suvidha

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class ProjectViewModel : ViewModel() {

    private val db = Firebase.firestore

    private val _projects = MutableStateFlow<List<Project>>(emptyList())
    val projects: StateFlow<List<Project>> = _projects

    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _translatedProjects = MutableStateFlow<List<Project>>(emptyList())
    val translatedProjects: StateFlow<List<Project>> = _translatedProjects

    init {
        loadProjects()
    }

    private fun loadProjects() {
        viewModelScope.launch {
            try {
                val snapshot = db.collection("projects").get().await()
                if (snapshot.isEmpty) {
                    addSampleData()
                } else {
                    val loaded = snapshot.documents.map { doc ->
                        Project(
                            id = doc.id,
                            name = doc.getString("name") ?: "",
                            type = doc.getString("type") ?: "",
                            location = doc.getString("location") ?: "",
                            status = doc.getString("status") ?: "",
                            progress = (doc.getLong("progress") ?: 0).toInt(),
                            description = doc.getString("description") ?: "",
                            budget = doc.getString("budget") ?: ""
                        )
                    }
                    _projects.value = loaded
                    _translatedProjects.value = loaded.map { project ->
                        Project(
                            id = project.id,
                            name = TranslationService.translate(project.name),
                            type = TranslationService.translate(project.type),
                            location = TranslationService.translate(project.location),
                            status = TranslationService.translate(project.status),
                            progress = project.progress,
                            description = TranslationService.translate(project.description),
                            budget = TranslationService.translate(project.budget)
                        )
                    }
                }
            } catch (e: Exception) {
                addSampleData()
            }
            _isLoading.value = false
        }
    }

    private suspend fun addSampleData() {
        val sampleProjects = listOf(
            hashMapOf("name" to "Main Road Repair", "type" to "Road", "location" to "Ward 1, Hosur", "status" to "Ongoing", "progress" to 60, "description" to "Repair and widening of main village road connecting market area.", "budget" to "5,00,000 Rupees"),
            hashMapOf("name" to "Water Supply Pipeline", "type" to "Water", "location" to "Ward 2, Hosur", "status" to "Completed", "progress" to 100, "description" to "New pipeline for clean drinking water supply to 200 households.", "budget" to "8,50,000 Rupees"),
            hashMapOf("name" to "Community Hall Construction", "type" to "Building", "location" to "Ward 3, Hosur", "status" to "Pending", "progress" to 0, "description" to "Construction of new community hall for village meetings and events.", "budget" to "12,00,000 Rupees"),
            hashMapOf("name" to "Street Light Installation", "type" to "Electricity", "location" to "Ward 4, Hosur", "status" to "Ongoing", "progress" to 40, "description" to "Installation of solar street lights across all main roads.", "budget" to "3,00,000 Rupees"),
            hashMapOf("name" to "Drainage System", "type" to "Sanitation", "location" to "Ward 1, Hosur", "status" to "Ongoing", "progress" to 75, "description" to "Underground drainage system to prevent waterlogging.", "budget" to "6,50,000 Rupees")
        )
        sampleProjects.forEach { db.collection("projects").add(it).await() }
        loadProjects()
    }

    fun submitFeedback(projectId: String, name: String, message: String, onDone: () -> Unit) {
        viewModelScope.launch {
            db.collection("feedback").add(
                hashMapOf(
                    "projectId" to projectId,
                    "name" to name,
                    "message" to message,
                    "timestamp" to System.currentTimeMillis()
                )
            ).await()
            onDone()
        }
    }
}
package com.grama.suvidha.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.grama.suvidha.ProjectViewModel
import com.grama.suvidha.TranslationService

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProjectDetailScreen(
    projectId: String,
    viewModel: ProjectViewModel,
    onBack: () -> Unit
) {
    val projects by viewModel.projects.collectAsState()
    val translatedProjects by viewModel.translatedProjects.collectAsState()

    val project = projects.find { it.id == projectId } ?: return
    val translatedProject = translatedProjects.find { it.id == projectId }

    var isKannada by remember { mutableStateOf(false) }
    val displayProject = if (isKannada && translatedProject != null) translatedProject else project

    var feedbackName by remember { mutableStateOf("") }
    var feedbackMessage by remember { mutableStateOf("") }
    var submitted by remember { mutableStateOf(false) }
    var isSubmitting by remember { mutableStateOf(false) }

    val statusColor = when {
        project.status.contains("Completed") -> Color(0xFF2E7D32)
        project.status.contains("Ongoing") -> Color(0xFF1565C0)
        else -> Color(0xFFE65100)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        displayProject.name,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            Icons.Default.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF1B5E20),
                    titleContentColor = Color.White
                ),
                actions = {
                    TextButton(onClick = { isKannada = !isKannada }) {
                        Text(
                            if (isKannada) "ENG" else "ಕನ್ನಡ",
                            color = Color.White,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(Color(0xFFF1F8E9))
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Project Info Card
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(4.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        if (isKannada) "ಯೋಜನೆ ವಿವರಗಳು" else "Project Details",
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        color = Color(0xFF1B5E20)
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    InfoRow(
                        label = if (isKannada) "ಪ್ರಕಾರ" else "Type",
                        value = displayProject.type
                    )
                    InfoRow(
                        label = if (isKannada) "ಸ್ಥಳ" else "Location",
                        value = displayProject.location
                    )
                    InfoRow(
                        label = if (isKannada) "ಬಜೆಟ್" else "Budget",
                        value = displayProject.budget
                    )
                    InfoRow(
                        label = if (isKannada) "ವಿವರಣೆ" else "Description",
                        value = displayProject.description
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            if (isKannada) "ಸ್ಥಿತಿ:" else "Status:",
                            fontWeight = FontWeight.SemiBold,
                            color = Color(0xFF1A1A1A)
                        )
                        Surface(
                            shape = RoundedCornerShape(20.dp),
                            color = statusColor.copy(alpha = 0.15f)
                        ) {
                            Text(
                                displayProject.status,
                                color = statusColor,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(
                                    horizontal = 12.dp,
                                    vertical = 4.dp
                                )
                            )
                        }
                    }
                }
            }

            // Progress Card
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(4.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            if (isKannada) "ಪ್ರಗತಿ" else "Progress",
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp,
                            color = Color(0xFF1B5E20)
                        )
                        Text(
                            if (isKannada) "${TranslationService.toKannadaNumber(project.progress)}%"
                            else "${project.progress}%",
                            fontWeight = FontWeight.Bold,
                            color = statusColor,
                            fontSize = 16.sp
                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    LinearProgressIndicator(
                        progress = { project.progress / 100f },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(12.dp),
                        color = statusColor,
                        trackColor = Color(0xFFE0E0E0)
                    )
                }
            }

            // Feedback Card
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(4.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        if (isKannada) "ಪ್ರತಿಕ್ರಿಯೆ ಸಲ್ಲಿಸಿ" else "Submit Feedback",
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        color = Color(0xFF1B5E20)
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    if (submitted) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                if (isKannada) "✅ ಪ್ರತಿಕ್ರಿಯೆ ಯಶಸ್ವಿಯಾಗಿ ಸಲ್ಲಿಸಲಾಗಿದೆ!"
                                else "✅ Feedback submitted successfully!",
                                color = Color(0xFF2E7D32),
                                fontWeight = FontWeight.SemiBold
                            )
                        }
                    } else {
                        OutlinedTextField(
                            value = feedbackName,
                            onValueChange = { feedbackName = it },
                            label = {
                                Text(if (isKannada) "ನಿಮ್ಮ ಹೆಸರು" else "Your Name")
                            },
                            textStyle = TextStyle(
                                color = Color(0xFF1A1A1A),
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Medium
                            ),
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedTextColor = Color(0xFF1A1A1A),
                                unfocusedTextColor = Color(0xFF1A1A1A),
                                focusedBorderColor = Color(0xFF1B5E20),
                                unfocusedBorderColor = Color(0xFF9E9E9E)
                            ),
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(8.dp)
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        OutlinedTextField(
                            value = feedbackMessage,
                            onValueChange = { feedbackMessage = it },
                            label = {
                                Text(if (isKannada) "ನಿಮ್ಮ ಪ್ರತಿಕ್ರಿಯೆ" else "Your Feedback or Issue")
                            },
                            textStyle = TextStyle(
                                color = Color(0xFF1A1A1A),
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Medium
                            ),
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedTextColor = Color(0xFF1A1A1A),
                                unfocusedTextColor = Color(0xFF1A1A1A),
                                focusedBorderColor = Color(0xFF1B5E20),
                                unfocusedBorderColor = Color(0xFF9E9E9E)
                            ),
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(120.dp),
                            shape = RoundedCornerShape(8.dp),
                            maxLines = 5
                        )
                        Spacer(modifier = Modifier.height(12.dp))
                        Button(
                            onClick = {
                                if (feedbackName.isNotBlank() && feedbackMessage.isNotBlank()) {
                                    isSubmitting = true
                                    viewModel.submitFeedback(
                                        projectId,
                                        feedbackName,
                                        feedbackMessage
                                    ) {
                                        isSubmitting = false
                                        submitted = true
                                    }
                                }
                            },
                            modifier = Modifier.fillMaxWidth(),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFF1B5E20)
                            ),
                            enabled = !isSubmitting
                        ) {
                            if (isSubmitting) {
                                CircularProgressIndicator(
                                    color = Color.White,
                                    modifier = Modifier.size(20.dp)
                                )
                            } else {
                                Text(
                                    if (isKannada) "ಪ್ರತಿಕ್ರಿಯೆ ಸಲ್ಲಿಸಿ" else "Submit Feedback",
                                    color = Color.White,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun InfoRow(label: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
    ) {
        Text(
            "$label: ",
            fontWeight = FontWeight.SemiBold,
            color = Color(0xFF1A1A1A),
            fontSize = 14.sp
        )
        Text(
            value,
            color = Color(0xFF1A1A1A),
            fontSize = 14.sp,
            fontWeight = FontWeight.Normal
        )
    }
}
package com.grama.suvidha.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.grama.suvidha.Project
import com.grama.suvidha.ProjectViewModel
import com.grama.suvidha.TranslationService

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProjectListScreen(viewModel: ProjectViewModel, onProjectClick: (String) -> Unit) {
    val projects by viewModel.projects.collectAsState()
    val translatedProjects by viewModel.translatedProjects.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    var isKannada by remember { mutableStateOf(false) }

    val displayProjects = if (isKannada && translatedProjects.isNotEmpty())
        translatedProjects else projects

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        if (isKannada) "ಗ್ರಾಮ ಸುವಿಧಾ" else "Grama Suvidha",
                        fontWeight = FontWeight.Bold
                    )
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
        if (isLoading) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    CircularProgressIndicator(color = Color(0xFF1B5E20))
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("Loading projects...", color = Color(0xFF1B5E20))
                }
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .background(Color(0xFFF1F8E9)),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                item {
                    Text(
                        if (isKannada) "ಅಭಿವೃದ್ಧಿ ಯೋಜನೆಗಳು" else "Development Projects",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color(0xFF1B5E20),
                        modifier = Modifier.padding(bottom = 4.dp)
                    )
                }
                items(displayProjects, key = { it.id }) { project ->
                    ProjectCard(
                        project = project,
                        isKannada = isKannada,
                        onClick = {
                            val original = projects.find { it.id == project.id }
                            original?.let { onProjectClick(it.id) }
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun ProjectCard(project: Project, isKannada: Boolean, onClick: () -> Unit) {
    val statusColor = when {
        project.status.contains("Completed") || project.status.contains("ಪೂರ್ಣ") -> Color(0xFF2E7D32)
        project.status.contains("Ongoing") || project.status.contains("ಪ್ರಗತಿ") -> Color(0xFF1565C0)
        else -> Color(0xFFE65100)
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    project.name,
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 16.sp,
                    color = Color(0xFF1A1A1A),
                    modifier = Modifier.weight(1f)
                )
                Surface(
                    shape = RoundedCornerShape(20.dp),
                    color = statusColor.copy(alpha = 0.15f)
                ) {
                    Text(
                        project.status,
                        color = statusColor,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 12.sp,
                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
                    )
                }
            }
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                "📍 ${project.location}",
                fontSize = 13.sp,
                color = Color.Gray
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                if (isKannada) "ಪ್ರಗತಿ: ${TranslationService.toKannadaNumber(project.progress)}%"
                else "Progress: ${project.progress}%",
                fontSize = 13.sp,
                color = Color(0xFF1B5E20),
                fontWeight = FontWeight.Medium
            )
            Spacer(modifier = Modifier.height(4.dp))
            LinearProgressIndicator(
                progress = { project.progress / 100f },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(8.dp),
                color = statusColor,
                trackColor = Color(0xFFE0E0E0)
            )
        }
    }
}
package com.grama.suvidha.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.grama.suvidha.ProjectViewModel
import com.grama.suvidha.ui.screens.ProjectListScreen
import com.grama.suvidha.ui.screens.ProjectDetailScreen

@Composable
fun AppNavigation(navController: NavHostController) {
    val viewModel: ProjectViewModel = viewModel()

    NavHost(navController = navController, startDestination = "project_list") {
        composable("project_list") {
            ProjectListScreen(
                viewModel = viewModel,
                onProjectClick = { projectId ->
                    navController.navigate("project_detail/$projectId")
                }
            )
        }
        composable("project_detail/{projectId}") { backStackEntry ->
            val projectId = backStackEntry.arguments?.getString("projectId") ?: ""
            ProjectDetailScreen(
                projectId = projectId,
                viewModel = viewModel,
                onBack = { navController.popBackStack() }
            )
        }
    }
}
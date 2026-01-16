package com.example.freetogame

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.freetogame.presentation.navigation.Screen
import com.example.freetogame.presentation.screens.detail.GameDetailScreen
import com.example.freetogame.presentation.screens.detail.GameDetailViewModel
import com.example.freetogame.presentation.screens.list.GameListScreen
import com.example.freetogame.presentation.screens.list.GameListViewModel
import com.example.freetogame.presentation.theme.iOSBackground
import com.example.freetogame.presentation.theme.iOSBlue
import com.example.freetogame.presentation.theme.iOSLightGray
import com.example.freetogame.presentation.theme.iOSSurface
import com.example.freetogame.presentation.util.ViewModelFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val iOSDarkColors = darkColorScheme(
                primary = iOSBlue,
                background = iOSBackground,
                surface = iOSSurface,
                surfaceVariant = iOSLightGray
            )
            MaterialTheme(colorScheme = iOSDarkColors) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppNavigation()
                }
            }
        }
    }
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val factory = ViewModelFactory()

    NavHost(
        navController = navController,
        startDestination = Screen.GameList.route
    ) {
        composable(Screen.GameList.route) {
            val viewModel: GameListViewModel = viewModel(factory = factory)
            GameListScreen(navController, viewModel)
        }
        composable(Screen.GameDetail.route) { backStackEntry ->
            val gameId = backStackEntry.arguments?.getString("gameId")?.toIntOrNull()
            if (gameId != null) {
                val viewModel: GameDetailViewModel = viewModel(factory = factory)
                LaunchedEffect(gameId) {
                    viewModel.getGameDetails(gameId)
                }
                GameDetailScreen(navController, viewModel)
            }
        }
    }
}

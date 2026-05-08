package com.yaroslav.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.yaroslav.myapplication.ui.theme.MovieBrowserTheme
import com.yaroslav.myapplication.view.DetailsScreen
import com.yaroslav.myapplication.view.FavoritesScreen
import com.yaroslav.myapplication.view.MainScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MovieBrowserTheme {
                AppNavigation()
            }
        }
    }
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "main_screen"
    ) {
        composable("main_screen") {
            MainScreen(
                onFavoritesClick = {
                    navController.navigate("favorites_screen")
                },
                onMovieClick = { id ->
                    navController.navigate("details_screen/$id")
                }
            )
        }

        composable("details_screen/{movieId}") { backStackEntry ->
            val movieIdString = backStackEntry.arguments?.getString("movieId")
            val movieId = movieIdString?.toIntOrNull() ?: return@composable

            DetailsScreen(
                movieId = movieId,
                onBackClick = { navController.popBackStack() }
            )
        }

        composable("favorites_screen") {
            FavoritesScreen(
                onBackClick = { navController.popBackStack() },
                onMovieClick = { id ->
                    navController.navigate("details_screen/$id")
                }
            )
        }
    }
}
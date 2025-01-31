package com.beeeam.feature.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.beeeam.feature.joke.JokeRoute
import com.beeeam.feature.search.SearchRoute

@Composable
fun SimpleJokeNavHost() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "joke",
    ) {
        composable("joke") {
            JokeRoute(navController = navController)
        }
        composable("Search") {
            SearchRoute()
        }
    }
}
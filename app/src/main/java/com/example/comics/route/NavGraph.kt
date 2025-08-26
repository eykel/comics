package com.example.comics.route

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.comics.ui.home.HomeScreenRoot
import com.example.comics.ui.theme.ComicsTheme

@Composable
fun NavGraph() {

    val navController = rememberNavController()

    ComicsTheme {
        Scaffold { innerPadding ->
            Column(Modifier.padding(innerPadding).fillMaxSize()) {
                NavHost(navController = navController, startDestination = Route.HomeScreen) {
                    composable<Route.HomeScreen> {

                        HomeScreenRoot()
                    }
//                    composable<Route.DetailScreen> {
//                        DetailScreen()
//                    }
                }
            }
        }
    }
}
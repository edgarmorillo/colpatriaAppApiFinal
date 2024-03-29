package com.example.prueba.ui.viewmodel

import DashboardScreen
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.prueba.ui.view.CompraCarteraScreen
import com.example.prueba.ui.view.ErrorCompra
import com.example.prueba.ui.view.SuccessCompra

@Composable
fun DashboardNavigator() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "dashboardScreen") {
        composable("dashboardScreen") {
            DashboardScreen(
                navigateToBuyDebt = {
                    navController.navigate("compraCarteraScreen")
                },
            )
        }
        composable("compraCarteraScreen") {
            CompraCarteraScreen(
                navigateToSuccessScreen = {
                    navController.navigate("successCompraScreen")
                },
                navigateToErrorScreen = {
                    navController.navigate("errorCompraScreen")
                },
            )
        }
        composable("successCompraScreen") {
            SuccessCompra(
                navigateToHome = {
                    navController.navigate("dashboardScreen")
                }
            )
        }

        composable("errorCompraScreen") {
            ErrorCompra(
                goBack = {
                    navController.popBackStack()
                },
            )
        }
    }
}

package com.ritesh.animatexcompose.presentaions.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.ritesh.animatexcompose.presentaions.code.screen.CodeScreen
import com.ritesh.animatexcompose.presentaions.details.screen.DetailsScreen
import com.ritesh.animatexcompose.presentaions.home.screen.HomeScreen
import com.ritesh.animatexcompose.presentaions.onboarding.screen.OnboardingScreen
import com.ritesh.animatexcompose.presentaions.splash.SplashScreen

@Composable
fun AnimateXApp(
    viewModel: NavigationViewModel = hiltViewModel()
) {
    val navController = rememberNavController()
    val isFirstLaunch by viewModel.isFirstLaunch.collectAsState()

    NavHost(navController = navController, startDestination = "splash") {
        composable("splash") {
            SplashScreen {
                navController.navigate(if (isFirstLaunch) "onBoarding" else "home"){
                    popUpTo("splash"){
                        inclusive = true
                    }
                }
            }
        }
        composable("onBoarding") {
            OnboardingScreen{
                viewModel.setFirstLaunchCompleted()
                navController.navigate("home"){
                    popUpTo("onBoarding"){
                        inclusive = true
                    }
                }
            }
        }
        composable("home") {
            HomeScreen(
                onNavigateToDetails = { animationId ->
                    navController.navigate("details/$animationId")
                }
            )
        }

        composable(
            route = "details/{animationId}",
            arguments = listOf(
                navArgument("animationId") { type = NavType.IntType }
            )
        ) {
            DetailsScreen(
                onNavigateBack = {
                    navController.popBackStack()
                },
                onNavigateToCode = { animationId->
                    navController.navigate("code/$animationId")
                }
            )
        }

        composable(
            route = "code/{animationId}",
            arguments = listOf(
                navArgument("animationId") { type = NavType.IntType }
            )
            ) {
            CodeScreen(
                onNavigateBack = {
                    navController.popBackStack()
                }
            )
        }
    }
}
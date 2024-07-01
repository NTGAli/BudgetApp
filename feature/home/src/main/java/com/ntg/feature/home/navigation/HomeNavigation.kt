package com.ntg.feature.home.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.ntg.feature.home.HomeRoute

const val Home_Route = "home_route"


fun NavController.navigateToForYou(navOptions: NavOptions) = navigate(Home_Route, navOptions)
fun NavController.navigateHomeScreen() = navigate(Home_Route)

fun NavGraphBuilder.homeScreen(onTopicClick: (String) -> Unit = {}) {
    composable(
        route = Home_Route,
    ) {
        HomeRoute()
    }
}

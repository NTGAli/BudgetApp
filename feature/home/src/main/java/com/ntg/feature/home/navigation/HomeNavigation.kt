package com.ntg.feature.home.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink
import com.ntg.feature.home.HomeRoute

const val Home_Route = "home_route"


fun NavController.navigateToForYou(navOptions: NavOptions) = navigate(Home_Route, navOptions)

fun NavGraphBuilder.homeScreen(onTopicClick: (String) -> Unit = {}) {
    composable(
        route = Home_Route,
    ) {
        HomeRoute()
    }
}

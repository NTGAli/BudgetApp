package com.ntg.feature.setup.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.ntg.feature.setup.CurrencyRoute
import com.ntg.feature.setup.SetupRoute

const val IS_EDIT = "isEdit"
const val Setup_Route = "setup_route"
const val Currency_Route = "currency_route/"


fun NavController.navigateToSetup(navOptions: NavOptions) = navigate(Setup_Route, navOptions)


fun NavGraphBuilder.setupScreen() {
    composable(
        route = Setup_Route,
    ) {
        SetupRoute()
    }


    composable(
        route = Currency_Route
    ){
        CurrencyRoute()
    }
}

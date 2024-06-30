package com.ntg.transaction.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.ntg.transaction.TransactionInputRute

const val TransactionInput_Route = "transaction_input_route"

fun NavController.navigateToTransaction(navOptions: NavOptions) = navigate(TransactionInput_Route, navOptions)

fun NavGraphBuilder.transactionScreen(
    navigateTOCategory:()-> Unit
) {
    composable(
        route = TransactionInput_Route,
    ) {
        TransactionInputRute(
            navigateTOCategory
        )
    }
}

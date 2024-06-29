package com.ntg.transaction.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.ntg.transaction.TransactionInputRute

const val IS_EDIT = "isEdit"
const val TransactionInput_Route = "transaction_input_route/{$IS_EDIT}"
const val Category_Route = "category_route"

fun NavController.navigateToCategory(){
    navigate(Category_Route)
}
fun NavGraphBuilder.transactionInputScreen(
    navigateTOCategory:()-> Unit
) {
    composable(
        route = TransactionInput_Route,
        arguments = listOf(
            navArgument(IS_EDIT) { type = NavType.BoolType },
        ),
    ) {
        TransactionInputRute(
            navigateTOCategory
        )
    }
}

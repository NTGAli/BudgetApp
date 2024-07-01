package com.ntg.feature.setup.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.ntg.feature.setup.BalanceCheckRoute
import com.ntg.feature.setup.CardViewRoute
import com.ntg.feature.setup.CurrencyRoute
import com.ntg.feature.setup.SetupRoute
import com.ntg.feature.setup.SetupViewModel

const val IS_EDIT = "isEdit"
const val Setup_Route = "setup_route"
const val CardView_Route = "cardView_route"
const val Currency_Route = "currency_route/"
const val BalanceCheck_Route = "balance_check_route/"


fun NavController.navigateToSetup(navOptions: NavOptions) = navigate(Setup_Route, navOptions)
fun NavController.navigateToCardViewScreen() = navigate(CardView_Route)
fun NavController.navigateToBalanceCheckScreen() = navigate(BalanceCheck_Route)
fun NavController.navigateToCurrencyFormatScreen() = navigate(Currency_Route)


fun NavGraphBuilder.setupScreen(
  setupViewModel: SetupViewModel,
  navigateToCardView:() -> Unit,
  navigateToBalanceCheckScreen:() -> Unit,
  navigateToCurrencyFormatScreen:() -> Unit,
  navigateToHome:() -> Unit,
) {
    composable(
        route = Setup_Route,
    ) {
        SetupRoute(
          navigateToCardView = navigateToCardView
        )
    }


    composable(
        route = Currency_Route
    ){
        CurrencyRoute()
    }

  composable(
    route = CardView_Route
  ){
    CardViewRoute(
      navigateToBalanceCheckScreen = navigateToBalanceCheckScreen,
      setupViewModel = setupViewModel
    )
  }


  composable(
    route = BalanceCheck_Route
  ){
    BalanceCheckRoute(
      navigateToCurrencyFormatScreen = navigateToCurrencyFormatScreen,
      navigateToHome = navigateToHome,
      setupViewModel = setupViewModel
    )
  }

}

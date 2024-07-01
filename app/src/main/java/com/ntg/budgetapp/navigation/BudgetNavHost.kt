package com.ntg.budgetapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import com.ntg.budgetapp.ui.BudgetAppState
import com.ntg.feature.category.navigation.categoryScreen
import com.ntg.feature.category.navigation.navigateToCategory
import com.ntg.feature.home.navigation.Home_Route
import com.ntg.feature.home.navigation.homeScreen
import com.ntg.feature.home.navigation.navigateHomeScreen
import com.ntg.feature.setup.SetupViewModel
import com.ntg.feature.setup.navigation.navigateToBalanceCheckScreen
import com.ntg.feature.setup.navigation.navigateToCardViewScreen
import com.ntg.feature.setup.navigation.navigateToCurrencyFormatScreen
import com.ntg.feature.setup.navigation.navigateToSetup
import com.ntg.feature.setup.navigation.setupScreen
import com.ntg.transaction.navigation.transactionScreen

@Composable
fun BudgetNavHost(
  appState: BudgetAppState,
  onShowSnackbar: suspend (String, String?) -> Boolean,
  modifier: Modifier = Modifier,
  startDestination: String = Home_Route,
) {
  val navController = appState.navController

  val setupViewModel: SetupViewModel = viewModel()

  NavHost(
    navController = navController,
    startDestination = startDestination,
    modifier = modifier,
  ) {
    homeScreen()
    transactionScreen(
      navigateTOCategory = navController::navigateToCategory
    )
    categoryScreen()
    setupScreen(
      setupViewModel = setupViewModel,
      navigateToCardView = navController::navigateToCardViewScreen,
      navigateToBalanceCheckScreen = navController::navigateToBalanceCheckScreen,
      navigateToCurrencyFormatScreen = navController::navigateToCurrencyFormatScreen,
      navigateToHome = navController::navigateHomeScreen
    )
  }
}

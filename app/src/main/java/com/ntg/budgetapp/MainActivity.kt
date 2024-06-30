package com.ntg.budgetapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.CompositionLocalProvider
import com.ntg.budgetapp.ui.BudgetApp
import com.ntg.budgetapp.ui.rememberBudgetAppState
import com.ntg.core.designsystem.theme.BudgetAppTheme
import dagger.hilt.android.AndroidEntryPoint
import com.ntg.core.analytics.LocalAnalyticsHelper
import com.ntg.core.analytics.AnalyticsHelper
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

  @Inject
  lateinit var analyticsHelper: AnalyticsHelper

  @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    setContent {


      val appState = rememberBudgetAppState(
        windowSizeClass = calculateWindowSizeClass(this),
//                networkMonitor = networkMonitor,
//                userNewsResourceRepository = userNewsResourceRepository,
//                timeZoneMonitor = timeZoneMonitor,
      )

      CompositionLocalProvider(
        LocalAnalyticsHelper provides analyticsHelper,
      ){

        BudgetAppTheme {
          BudgetApp(appState)
        }

      }
    }
  }
}

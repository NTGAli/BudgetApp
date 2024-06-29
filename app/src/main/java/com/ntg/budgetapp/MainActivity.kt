package com.ntg.budgetapp

import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.ntg.budgetapp.ui.BudgetApp
import com.ntg.budgetapp.ui.rememberBudgetAppState
import com.ntg.core.designsystem.theme.BudgetAppTheme
import com.ntg.core.model.data.DarkThemeConfig
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
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

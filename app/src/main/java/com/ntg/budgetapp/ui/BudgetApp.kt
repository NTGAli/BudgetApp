package com.ntg.budgetapp.ui

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTagsAsResourceId
import com.ntg.budgetapp.R
import com.ntg.budgetapp.navigation.BudgetNavHost
import com.ntg.budgetapp.navigation.TopLevelDestination
import com.ntg.core.designsystem.component.BottomNavigation
import com.ntg.core.designsystem.component.NavigationRail
import com.ntg.core.designsystem.component.scrollbar.BudgetBackground
import com.ntg.core.designsystem.model.NavigationItem

@Composable
fun BudgetApp(appState: BudgetAppState, modifier: Modifier = Modifier) {

    var showSettingsDialog by rememberSaveable { mutableStateOf(false) }
    val snackbarHostState = remember { SnackbarHostState() }


    BudgetBackground(modifier = modifier) {
        BudgetApp(
            appState = appState,
            snackbarHostState = snackbarHostState,
            showSettingsDialog = showSettingsDialog,
            onSettingsDismissed = { showSettingsDialog = false },
            onTopAppBarActionClick = { showSettingsDialog = true },
        )
    }
}


@OptIn(ExperimentalComposeUiApi::class)
@Composable
internal fun BudgetApp(
    appState: BudgetAppState,
    snackbarHostState: SnackbarHostState,
    showSettingsDialog: Boolean,
    onSettingsDismissed: () -> Unit,
    onTopAppBarActionClick: () -> Unit,
    modifier: Modifier = Modifier,
) {


    Scaffold(
        modifier = modifier.semantics {
            testTagsAsResourceId = true
        },
        containerColor = Color.Transparent,
        contentColor = MaterialTheme.colorScheme.onBackground,
        contentWindowInsets = WindowInsets(0, 0, 0, 0),
        snackbarHost = { SnackbarHost(snackbarHostState) },
        bottomBar = {
            if (appState.shouldShowBottomBar) {
                AppBottomBar(
                  onNavigateToDestination = appState::navigateToTopLevelDestination
                )
            }
        },
    ){padding ->
        Row(
            Modifier
                .fillMaxSize()
                .padding(padding)
                .consumeWindowInsets(padding)
                .windowInsetsPadding(
                    WindowInsets.safeDrawing.only(
                        WindowInsetsSides.Horizontal,
                    ),
                ),
        ) {

            if (appState.shouldShowNavRail) {
                NavigationRail(
                )
            }


            Column(Modifier.fillMaxSize()) {
                // Show the top app bar on top level destinations.
                val destination = appState.currentTopLevelDestination
                val shouldShowTopAppBar = destination != null
                if (destination != null) {
//                    BudgetAppbar()
                }

                BudgetNavHost(
                    appState = appState,
                    onShowSnackbar = { message, action ->
                        snackbarHostState.showSnackbar(
                            message = message,
                            actionLabel = action,
                            duration = SnackbarDuration.Short,
                        ) == SnackbarResult.ActionPerformed
                    },
                    modifier = if (shouldShowTopAppBar) {
                        Modifier.consumeWindowInsets(
                            WindowInsets.safeDrawing.only(WindowInsetsSides.Top),
                        )
                    } else {
                        Modifier
                    },
                )
            }
        }
    }
}



@Composable
private fun AppBottomBar(
  onNavigateToDestination: (TopLevelDestination) -> Unit,
){
    val navs = listOf(
        NavigationItem(
            1,
            "test",
            painterResource(id = R.drawable.home),
            painterResource(id = R.drawable.home),
            isSelected = true
        ),
        NavigationItem(
            2,
            "test",
            painterResource(id = R.drawable.user_circle),
            painterResource(id = R.drawable.user_circle),
            isSelected = false
        )
    )

    BottomNavigation(modifier = Modifier, items = navs) {
      onNavigateToDestination(TopLevelDestination.SETUP)
      Log.d("BottomNavigation", "$it")
    }
}

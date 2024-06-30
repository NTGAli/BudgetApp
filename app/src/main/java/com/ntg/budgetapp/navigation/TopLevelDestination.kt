package com.ntg.budgetapp.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.ui.graphics.vector.ImageVector
import com.ntg.budgetapp.R

enum class TopLevelDestination(
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val iconTextId: Int,
    val titleTextId: Int,
) {
    HOME(
        selectedIcon = Icons.Rounded.Add,
        unselectedIcon = Icons.Rounded.Add,
        iconTextId = R.string.app_name,
        titleTextId = R.string.app_name,
    ),

  TRANSACTION(
    selectedIcon = Icons.Rounded.Add,
    unselectedIcon = Icons.Rounded.Add,
    iconTextId = R.string.app_name,
    titleTextId = R.string.app_name,
  ),
}

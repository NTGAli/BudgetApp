package com.ntg.feature.category.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.ntg.feature.category.CategoryRoute

const val Category_Route = "category_route"

fun NavController.navigateToCategory(){
  navigate(Category_Route)
}
fun NavGraphBuilder.categoryScreen()
{

  composable(
    route = Category_Route,
  ) {
    CategoryRoute()
  }
}

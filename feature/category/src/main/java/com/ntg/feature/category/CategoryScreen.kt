package com.ntg.feature.category

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ntg.core.commom.util.Categories
import com.ntg.core.commom.util.generateUniqueFiveDigitId
import com.ntg.core.designsystem.component.AutoCompleteDropdown
import com.ntg.core.designsystem.component.ButtonBottomBar
import com.ntg.core.designsystem.component.SheetSearch
import com.ntg.core.designsystem.component.TextField
import com.ntg.core.designsystem.icon.BudgetIcon
import com.ntg.core.designsystem.icon.CategoryIcons
import com.ntg.core.designsystem.model.CategoryIcon
import com.ntg.core.model.Category
import kotlinx.coroutines.launch

@Composable
fun CategoryRoute(
  viewModel: CategoryViewModel = hiltViewModel(),
) {
  CategoryScreen { category ->
    viewModel.insertCategory(
      category,
    )
  }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryScreen(onClick: (Category) -> Unit) {

  val list = listOf(
    "Car",
    "Home",
    "Computer",
  )

  val title = remember {
    mutableStateOf("")
  }

  val query = remember {
    mutableStateOf("")
  }

  var icon by remember {
    mutableStateOf<CategoryIcon?>(null)
  }

  var openCatSheet by remember {
    mutableStateOf(false)
  }

  var expanded by remember {
    mutableStateOf(false)
  }

  val coroutineScope = rememberCoroutineScope()
  val state = rememberModalBottomSheetState()

  Scaffold(
    bottomBar = {
      ButtonBottomBar {
        onClick.invoke(
          Category(
            id = generateUniqueFiveDigitId(),
            title = title.value,
            iconId = icon?.id,
            dateCreated = System.currentTimeMillis().toString(),
          ),
        )
      }
    },
  ) {
    Column(Modifier.padding(it)) {

      TextField(
        modifier = Modifier
            .padding(horizontal = 32.dp)
            .padding(top = 24.dp),
        label = stringResource(id = R.string.icon),
        trailingIcon = {
          Icon(painter = painterResource(id = BudgetIcon.ArrowRight), contentDescription = null)
        },
        readOnly = true,
        leadingIcon = if (icon != null) {
          {
            Image(painter = painterResource(id = icon!!.res), contentDescription = icon!!.tags)
          }
        } else null,
      ) {
        openCatSheet = true
      }

      TextField(
        modifier = Modifier
            .padding(horizontal = 32.dp)
            .padding(top = 16.dp),
        value = title,
        label = stringResource(id = R.string.title),
      )


      AutoCompleteDropdown(
          modifier = Modifier.padding(horizontal = 32.dp),
          query = title,
          list = Categories,
      ) { item ->
        title.value = item
        expanded = false
      }
    }
  }

  if (!openCatSheet) {
    query.value = ""
  }

  if (openCatSheet) {
    ModalBottomSheet(
        onDismissRequest = {
            openCatSheet = false
            coroutineScope.launch {
                state.hide()
            }
        },
        sheetState = state,
    ) {

      val finalCategories = CategoryIcons.filter { it.tags.contains(query.value) }

      SheetSearch(
        modifier = Modifier
            .padding(top = 8.dp, bottom = 24.dp)
            .padding(horizontal = 32.dp),
        query = query,
      )

      LazyVerticalGrid(
        modifier = Modifier
            .padding(horizontal = 32.dp)
            .padding(bottom = 32.dp),
        columns = GridCells.Fixed(8),
      ) {

        items(finalCategories.size) { index ->
          Box(
            modifier = Modifier
                .clip(RoundedCornerShape(4.dp))
                .clickable {
                    icon = finalCategories.get(index)
                    coroutineScope.launch {
                        state.hide()
                        openCatSheet = false
                    }
                },
            contentAlignment = Alignment.Center,
          ) {
            Image(
              modifier = Modifier
                  .size(32.dp)
                  .padding(4.dp),
              painter = painterResource(id = finalCategories.get(index).res), contentDescription = null,
            )
          }
        }

      }
    }
  }


}

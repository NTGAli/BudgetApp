package com.ntg.feature.home

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ntg.core.designsystem.component.AmountReport
import com.ntg.core.designsystem.component.BudgetTabRow
import com.ntg.core.designsystem.component.Card
import com.ntg.core.designsystem.icon.BudgetIcon
import com.ntg.core.model.Card
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Thread.sleep

@Composable
fun HomeRoute(
  viewModel: HomeViewModel = hiltViewModel(),
) {
  val card by viewModel.selectedCard.collectAsStateWithLifecycle(null)

  HomeScreen(
    card = card
  )

}

@Composable
fun HomeScreen(
  modifier: Modifier = Modifier,
  card: Card?,
) {
  Box(
    modifier = modifier
      .fillMaxSize(),
  ) {

    LazyColumn {
      topCardView(card)

      transactions()

    }

  }
}


private fun LazyListScope.topCardView(
  card: Card?
) {

  item {
    var expanded by remember {
      mutableStateOf(false)
    }

    var heightApplied by remember {
      mutableStateOf(false)
    }

    LaunchedEffect(key1 = Unit) {
      CoroutineScope(Dispatchers.IO).launch {
        sleep(300)
        expanded = true
      }
    }

    Card(
      modifier = Modifier.padding(horizontal = 24.dp, vertical = 16.dp),
      card = ImageVector.vectorResource(BudgetIcon.sampleCard),
      cardNumber = card?.number.orEmpty(),
      name = card?.name.orEmpty(),
      fullView = expanded,
    ) {
      expanded = !expanded
    }

    AmountReport(
      modifier = Modifier.padding(horizontal = 24.dp),
      outcome = 200000, income = 150000,
    )
  }

}


@OptIn(ExperimentalFoundationApi::class)
private fun LazyListScope.transactions() {

  item {

    val pagerState = rememberPagerState { 3 }

    BudgetTabRow(
      modifier = Modifier.padding(top = 24.dp),
      tabData =
      listOf(Pair(1, "Activities"), Pair(2, "Overview"), Pair(3, "Categories")),
      pagerState = pagerState,
    )
    Log.d("BudgetTabRow", pagerState.currentPage.toString())

    HorizontalPager(
//            modifier = Modifier.padding(paddingValues),
      state = pagerState,
    ) { index ->
      Column(
        modifier = Modifier.fillMaxSize(),
      ) {


        Box(
          modifier = Modifier
            .fillMaxSize(),
//                        .background(MaterialTheme.colorScheme.secondary)
        ) {

          Text(
            text = pagerState.currentPage.toString(),
            style = TextStyle(fontSize = 48.sp, color = MaterialTheme.colorScheme.onBackground),
          )

        }
//                if (tabData[index] == stringResource(id = R.string.participents)) {
//                    ParticipatesScreen(navController = navHostController, stepViewModel, uid)
//                } else {
//                    WinnersScreen(navController = navHostController, stepViewModel)
//                }
      }
    }


  }

}

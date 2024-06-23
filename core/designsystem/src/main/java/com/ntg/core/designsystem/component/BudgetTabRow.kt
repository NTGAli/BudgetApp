package com.ntg.core.designsystem.component

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun BudgetTabRow(
    modifier: Modifier = Modifier,
    pagerState: PagerState = rememberPagerState { 0 },
    tabData: List<Pair<Int, String>>
){

    val tabIndex = pagerState.currentPage
    val coroutineScope = rememberCoroutineScope()

    TabRow(
        modifier = modifier,
        selectedTabIndex = tabIndex,
        contentColor = MaterialTheme.colorScheme.primary,
        containerColor = MaterialTheme.colorScheme.background,
        indicator = {tabPositions ->
            if (pagerState.currentPage < tabPositions.size) {
                TabRowDefaults.SecondaryIndicator(
                    Modifier.tabIndicatorOffset(tabPositions[pagerState.currentPage])
                        .padding(horizontal = 8.dp)
                )
            }
        }
    ) {
        tabData.forEachIndexed { index, item ->
            Tab(
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .clip(RoundedCornerShape(8.dp))
                    ,
                text = { Text(item.second) },
                selected = tabIndex == index,
                onClick = {

                    coroutineScope.launch {
                        pagerState.animateScrollToPage(index)
                    }
                },
                selectedContentColor = MaterialTheme.colorScheme.primary,
                unselectedContentColor = MaterialTheme.colorScheme.secondary,
            )
        }
    }
}

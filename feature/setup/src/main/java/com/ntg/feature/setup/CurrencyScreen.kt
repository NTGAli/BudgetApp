package com.ntg.feature.setup

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.motionEventSpy
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CurrencyRoute() {
    CurrencyScreen()
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun CurrencyScreen() {

    val sample: ArrayList<Int> = arrayListOf()

    for (i in 0 until 10000) {
        sample.add(i)
    }

    val lazyState = rememberLazyListState()

//    Log.d(
//        "rememberLazyListState",
//        "${lazyState.layoutInfo.viewportSize} -- " +
//                "${lazyState.layoutInfo.viewportEndOffset} --- " +
//                "${lazyState.layoutInfo.reverseLayout} ---- " +
//                "${lazyState.firstVisibleItemScrollOffset}"
//    )


    val minusHeight = lazyState.layoutInfo.viewportStartOffset

    val scroll = rememberLazyListState()
    val offset = rememberCurrentOffset(scroll)

    Log.d(
        "rememberLazyListState","${offset.value}")


    Scaffold(
        topBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height((540 - (offset.value)).dp)
                    .background(Color.Blue)
            )
        }
    ) {
//        Content(it)

        LazyColumn(
            modifier = Modifier.padding(it)
                .onGloballyPositioned {

                }
                .motionEventSpy {
                                Log.d("motionEventSpy", it.toString())
                },
            state = scroll
        ) {

            items(sample.size) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = it.toString(), style = TextStyle(fontSize = 24.sp)
                )
            }

//            item {
//                Box(modifier = Modifier
//                    .fillMaxWidth()
//                    .height(1200.dp)
//                    .background(Green))
//            }
//
//            item {
//                Box(modifier = Modifier
//                    .fillMaxWidth()
//                    .height(1200.dp)
//                    .background(Red))
//            }

        }

    }


}


@Composable
private fun Content(paddingValues: PaddingValues) {

    Column {

    }

}


@Composable
fun rememberCurrentOffset(state: LazyListState): State<Int> {
    val position = remember { derivedStateOf { state.firstVisibleItemIndex } }
    val itemOffset = remember { derivedStateOf { state.firstVisibleItemScrollOffset } }
    val lastPosition = rememberPrevious(position.value)
    val lastItemOffset = rememberPrevious(itemOffset.value)
    val currentOffset = remember { mutableStateOf(0) }

    LaunchedEffect(position.value, itemOffset.value) {
        if (lastPosition == null || position.value == 0) {
            currentOffset.value = itemOffset.value
        } else if (lastPosition == position.value) {
            currentOffset.value += (itemOffset.value - (lastItemOffset ?: 0))
        } else if (lastPosition > position.value) {
            currentOffset.value -= (lastItemOffset ?: 0)
        } else { // lastPosition.value < position.value
            currentOffset.value += itemOffset.value
        }
    }

    return currentOffset
}

@Composable
fun <T> rememberPrevious(
    current: T,
    shouldUpdate: (prev: T?, curr: T) -> Boolean = { a: T?, b: T -> a != b },
): T? {
    val ref = rememberRef<T>()

    // launched after render, so the current render will have the old value anyway
    SideEffect {
        if (shouldUpdate(ref.value, current)) {
            ref.value = current
        }
    }

    return ref.value
}

@Composable
fun <T> rememberRef(): MutableState<T?> {
    // for some reason it always recreated the value with vararg keys,
    // leaving out the keys as a parameter for remember for now
    return remember() {
        object: MutableState<T?> {
            override var value: T? = null

            override fun component1(): T? = value

            override fun component2(): (T?) -> Unit = { value = it }
        }
    }
}

package com.ntg.transaction

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ntg.budgetapp.feature.transaction.R
import com.ntg.core.commom.util.formatDate
import com.ntg.core.commom.util.getCurrentTime
import com.ntg.core.designsystem.component.Button
import com.ntg.core.designsystem.component.ButtonSize
import com.ntg.core.designsystem.component.ButtonStyle
import com.ntg.core.designsystem.component.Chips
import com.ntg.core.designsystem.component.DatePicker
import com.ntg.core.designsystem.component.ImagePicker
import com.ntg.core.designsystem.component.SampleItem
import com.ntg.core.designsystem.component.SwitchText
import com.ntg.core.designsystem.component.TextDivider
import com.ntg.core.designsystem.component.TextField
import com.ntg.core.designsystem.component.TimePicker
import com.ntg.core.designsystem.icon.BudgetIcon
import com.ntg.core.designsystem.icon.CategoryIcons
import com.ntg.core.model.Category
import kotlinx.coroutines.launch
import java.util.Date

@Composable
fun TransactionInputRute(
  navigateToCategory: () -> Unit,
  modifier: Modifier = Modifier,
  viewModel: TransactionViewModel = hiltViewModel(),
) {

  val categories by viewModel.categories.collectAsStateWithLifecycle(null)

  TransactionInputScreen(
    navigateToCategory,
    categories,
  )
}


@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
private fun TransactionInputScreen(
  navigateToCategory: () -> Unit,
  categories: List<Category>? = listOf(),
) {

  Log.d("TransactionInputScreen", "$categories")

  val focused = remember { mutableStateOf("") }
  val tag = remember { mutableStateOf("") }
  val tags = remember {
    mutableStateListOf<String>()
  }

  val dateSelected = remember { mutableStateOf(formatDate(Date(System.currentTimeMillis()))) }
  val timeSelected = remember { mutableStateOf(getCurrentTime()) }

  val showDatePicker = remember {
    mutableStateOf(false)
  }

  val showTimePicker = remember {
    mutableStateOf(false)
  }

  var isOpenSheet by remember {
    mutableStateOf(false)
  }

  var selectedCategory by remember {
    mutableStateOf<Category?>(null)
  }

  val coroutineScope = rememberCoroutineScope()
  val state = rememberModalBottomSheetState()


  Box(modifier = Modifier.fillMaxHeight()) {
    Column(
      modifier = Modifier
        .padding(horizontal = 32.dp)
        .verticalScroll(rememberScrollState())
        .padding(bottom = 64.dp),
    ) {

      SwitchText(
        modifier = Modifier
          .padding(top = 16.dp)
          .fillMaxWidth(),
        firstText = "Outcome", secondText = "Income",
      )

      TextField(
        modifier = Modifier.padding(top = 16.dp),
        value = focused,
        label = "Amount",
        trailingIcon = { Text(text = "lbs", color = MaterialTheme.colorScheme.outline) },
      )

      TextField(
        modifier = Modifier.padding(top = 16.dp),
        value = dateSelected,
        label = "Date",
        readOnly = true,
      ) {
        showDatePicker.value = true
      }

      TextField(
        modifier = Modifier.padding(top = 16.dp),
        value = timeSelected,
        label = "Time",
        readOnly = true,
      ) {
        showTimePicker.value = true
      }

      ImagePicker(
        modifier = Modifier.padding(top = 24.dp),
      )

      TextField(
        modifier = Modifier.padding(top = 16.dp),
        value = mutableStateOf(selectedCategory?.title.orEmpty()),
        leadingIcon =
        if (selectedCategory != null) {
          {
            Image(
              painter = painterResource(id = CategoryIcons.first { it.id == selectedCategory?.iconId }.res),
              contentDescription = null,
            )

          }
        }else null
       ,
        label = "Category",
        readOnly = true,
      ) {
        isOpenSheet = true
      }

      TextDivider(
        modifier = Modifier.padding(top = 16.dp),
        title = "Tags",
      )


      TextField(
        modifier = Modifier.padding(top = 16.dp),
        value = tag,
        label = "Tag",
        trailingIcon = {
          IconButton(
            onClick = {
              if (tag.value.isNotEmpty()) {
                tags.add(tag.value)
                tag.value = ""
              }
            },
          ) {
            Icon(imageVector = Icons.Rounded.Add, contentDescription = null)
          }
        },
      )

      FlowRow(
        modifier = Modifier.padding(8.dp),
      ) {
        repeat(tags.size) { index ->
          Chips(
            modifier = Modifier.padding(top = 8.dp, end = 8.dp),
            dismiss = false,
            title = tags[index],
            isSelected = true,
            onLongClick = {
              tag.value = tags[index]
              tags.remove(tags[index])
            }
          ) {
            tags.remove(tags[index])
          }
        }
      }


      TextField(
        modifier = Modifier.padding(top = 24.dp),
        value = focused,
        label = "Description",
        singleLine = false,
        maxLine = 5,
        minLine = 5,
      )


      Spacer(modifier = Modifier.padding(64.dp))

    }


    BottomBar()

  }

//    val date = remember {
//        Calendar.getInstance().apply {
//            set(Calendar.YEAR, 2023)
//            set(Calendar.MONTH, 7)
//            set(Calendar.DAY_OF_MONTH, 23)
//        }.timeInMillis
//    }

  val datePickerState = rememberDatePickerState(
    initialSelectedDateMillis = System.currentTimeMillis(),
    yearRange = 2015..2030,
  )


  val context = LocalContext.current

  // date picker component
  DatePicker(showDatePicker) {
    dateSelected.value = formatDate(it)
  }


  TimePicker(showTimePicker = showTimePicker) {
    timeSelected.value = it
  }


  if (isOpenSheet) {

    ModalBottomSheet(
      sheetState = state,
      onDismissRequest = {
        isOpenSheet = false
      },
    ) {

      Column {

        Button(
          modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp),
          text = stringResource(id = R.string.add_new_category), style = ButtonStyle.TextOnly,
          iconStart = painterResource(
            id = BudgetIcon.add,
          ),
          size = ButtonSize.MD,
        ) {
          coroutineScope.launch {
            state.hide()
            isOpenSheet = false
          }
          navigateToCategory()
        }

        LazyColumn(
          modifier = Modifier.padding(bottom = 32.dp),
        ) {
          items(categories.orEmpty()) { cat ->
            SampleItem(
              modifier = Modifier
                .padding(top = 8.dp)
                .padding(horizontal = 24.dp),
              title = cat.title, imagePainter = painterResource(id = CategoryIcons.first { it.id == cat.iconId }.res),
            ) {
              selectedCategory = cat
              coroutineScope.launch {
                state.hide()
                isOpenSheet = false
              }
            }
          }
        }
      }

    }

  }


}


@Composable
private fun BoxScope.BottomBar() {

  Column(
    modifier = Modifier
      .align(Alignment.BottomCenter)
      .background(MaterialTheme.colorScheme.background),
  ) {
    HorizontalDivider(color = MaterialTheme.colorScheme.surfaceVariant)
    Button(
      modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 16.dp, horizontal = 32.dp),
      text = "submit", size = ButtonSize.XL,
    )
  }

}

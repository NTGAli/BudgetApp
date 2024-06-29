package com.ntg.transaction

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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ntg.core.commom.util.formatDate
import com.ntg.core.commom.util.getCurrentTime
import com.ntg.core.designsystem.component.Button
import com.ntg.core.designsystem.component.ButtonSize
import com.ntg.core.designsystem.component.Chips
import com.ntg.core.designsystem.component.DatePicker
import com.ntg.core.designsystem.component.ImagePicker
import com.ntg.core.designsystem.component.SwitchText
import com.ntg.core.designsystem.component.TextDivider
import com.ntg.core.designsystem.component.TextField
import com.ntg.core.designsystem.component.TimePicker
import com.ntg.insert_transaction.TransactionViewModel
import java.util.Date

@Composable
fun TransactionInputRute(
  navigateToCategory:()-> Unit,
  modifier: Modifier = Modifier,
  viewModel: TransactionViewModel = hiltViewModel(),
) {
    TransactionInputScreen(
        navigateToCategory
    )
}


@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
private fun TransactionInputScreen(
    navigateToCategory:()-> Unit
) {

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

    Box(modifier = Modifier.fillMaxHeight()) {
        Column(
            modifier = Modifier
                .padding(horizontal = 32.dp)
                .verticalScroll(rememberScrollState())
                .padding(bottom = 64.dp)
        ) {

            SwitchText(
                modifier = Modifier
                    .padding(top = 16.dp)
                    .fillMaxWidth(),
                firstText = "Outcome", secondText = "Income"
            )

            TextField(
                modifier = Modifier.padding(top = 16.dp),
                value = focused,
                label = "Amount",
                trailingIcon = { Text(text = "lbs", color = MaterialTheme.colorScheme.outline) }
            )

            TextField(
                modifier = Modifier.padding(top = 16.dp),
                value = dateSelected,
                label = "Date",
                readOnly = true
            ) {
                showDatePicker.value= true
            }

            TextField(
                modifier = Modifier.padding(top = 16.dp),
                value = timeSelected,
                label = "Time",
                readOnly = true
            ){
                showTimePicker.value = true
            }

            ImagePicker(
                modifier = Modifier.padding(top = 24.dp)
            )

            TextField(
                modifier = Modifier.padding(top = 16.dp),
                value = focused,
                label = "Category",
                readOnly = true
            ){
                navigateToCategory()
            }

            TextDivider(
                modifier = Modifier.padding(top = 16.dp),
                title = "Tags"
            )


            TextField(
                modifier = Modifier.padding(top = 16.dp),
                value = tag,
                label = "Tag",
                trailingIcon = {
                    IconButton(onClick = {
                        if (tag.value.isNotEmpty()){
                            tags.add(tag.value)
                            tag.value = ""
                        }
                    }) {
                        Icon(imageVector = Icons.Rounded.Add, contentDescription = null)
                    }
                }
            )

            FlowRow(
                modifier = Modifier.padding(8.dp),
            ) {
                repeat(tags.size) { index ->
                    Chips(
                        modifier = Modifier.padding(top = 8.dp, end = 8.dp),
                        dismiss = false,
                        title = tags[index],
                        isSelected = true
                    ) {

                    }
                }
            }


            TextField(
                modifier = Modifier.padding(top = 24.dp),
                value = focused,
                label = "Description",
                singleLine = false,
                maxLine = 5,
                minLine = 5
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
        yearRange = 2015..2030
    )


    val context = LocalContext.current

    // date picker component
    DatePicker(showDatePicker){
        dateSelected.value = formatDate(it)
    }


    TimePicker(showTimePicker = showTimePicker){
        timeSelected.value = it
    }


}


@Composable
private fun BoxScope.BottomBar() {

    Column(
        modifier = Modifier
            .align(Alignment.BottomCenter)
            .background(MaterialTheme.colorScheme.background)
    ) {
        HorizontalDivider(color = MaterialTheme.colorScheme.surfaceVariant)
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp, horizontal = 32.dp),
            text = "submit", size = ButtonSize.XL
        )
    }

}

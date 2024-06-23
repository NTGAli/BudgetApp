package com.ntg.core.designsystem.component

import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import java.util.Calendar
import java.util.Date

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePicker(
    visible:MutableState<Boolean>,
    dateSelected:(Date) -> Unit
){
    val timePickerState = rememberTimePickerState()
    val datePickerState = rememberDatePickerState(
        initialSelectedDateMillis = System.currentTimeMillis(),
        yearRange = 2015..2030
    )

    if (visible.value){
        DatePickerDialog(
            onDismissRequest = { visible.value = false },
            confirmButton = {
                Button(text = "Select", style = ButtonStyle.TextOnly, size = ButtonSize.MD) {
                    val selectedDate = Calendar.getInstance().apply {
                        timeInMillis = datePickerState.selectedDateMillis!!
                    }
                    dateSelected(selectedDate.time)
                    visible.value = false
                }
            },
            dismissButton = {
                Button(
                    text = "Close",
                    style = ButtonStyle.TextOnly,
                    type = ButtonType.Error,
                    size = ButtonSize.MD
                ) {
                    visible.value = false
                }
            },
        )
        {
            androidx.compose.material3.DatePicker(state = datePickerState)
        }
    }

}

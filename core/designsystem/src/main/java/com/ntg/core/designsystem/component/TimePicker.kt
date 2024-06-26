package com.ntg.core.designsystem.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import java.util.Calendar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimePicker(
    showTimePicker: MutableState<Boolean>,
    timeSelected: (String) -> Unit
) {
    val timeState = rememberTimePickerState(
        initialHour = Calendar.HOUR_OF_DAY,
        initialMinute = Calendar.MINUTE,
        is24Hour = false
    )

    // time picker component
    if (showTimePicker.value) {
        TimePickerDialog("Select Time",
            onCancel = {
                showTimePicker.value = false
            },
            onConfirm = {
                timeSelected.invoke("${timeState.hour}:${timeState.minute}")
                showTimePicker.value = false
            },
            content = {
                androidx.compose.material3.TimePicker(state = timeState)
            })
    }
}


@Composable
private fun TimePickerDialog(
    title: String = "Select Time",
    onCancel: () -> Unit,
    onConfirm: () -> Unit,
    toggle: @Composable () -> Unit = {},
    content: @Composable () -> Unit,
) {
    Dialog(
        onDismissRequest = onCancel,
        properties = DialogProperties(
            usePlatformDefaultWidth = false
        ),
    ) {
        Surface(
            shape = MaterialTheme.shapes.extraLarge,
            tonalElevation = 6.dp,
            modifier = Modifier
                .width(IntrinsicSize.Min)
                .height(IntrinsicSize.Min)
                .background(
                    shape = MaterialTheme.shapes.extraLarge,
                    color = MaterialTheme.colorScheme.surface
                ),
        ) {
            Column(
                modifier = Modifier.padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 20.dp),
                    text = title,
                    style = MaterialTheme.typography.labelMedium
                )
                content()
                Row(
                    modifier = Modifier
                        .height(40.dp)
                        .fillMaxWidth()
                ) {
                    toggle()
                    Spacer(modifier = Modifier.weight(1f))
                    Button(text = "Cancel", style = ButtonStyle.TextOnly, size = ButtonSize.MD, type = ButtonType.Error){
                        onCancel()
                    }

                    Button(text = "Submit", style = ButtonStyle.TextOnly, size = ButtonSize.MD){
                        onConfirm()
                    }

                }
            }
        }
    }
}

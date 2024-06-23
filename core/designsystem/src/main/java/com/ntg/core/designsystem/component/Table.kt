package com.ntg.core.designsystem.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.ntg.core.designsystem.util.TableData

@Composable
fun Table(
  modifier: Modifier = Modifier,
  items: List<TableData>,
  first: String,
  second: String
){


    Column(modifier = modifier) {
        Row(modifier = Modifier.padding(horizontal = 8.dp).padding(bottom = 4.dp)) {
            Text(
                modifier = Modifier.weight(1f),
                text = first, style = MaterialTheme.typography.labelLarge)
            Text(text = second, style = MaterialTheme.typography.labelLarge)
        }
        Column(
            modifier = Modifier
                .clip(RoundedCornerShape(16.dp))
                .background(
                    shape = RoundedCornerShape(16.dp),
                    color = MaterialTheme.colorScheme.primaryContainer
                )
        ) {
            repeat(items.size){
                Item(item = items[it],items.size - 1 != it)
            }
        }
    }

}

@Composable
private fun Item(
    item: TableData,
    setDivider: Boolean
){

    Column(modifier = Modifier.padding(horizontal = 16.dp)) {
        Row(
            modifier = Modifier.padding(vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                modifier = Modifier.size(24.dp),
                painter = item.catImage, contentDescription = null)

            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 16.dp)
            ) {
                Text(text = item.title, style = MaterialTheme.typography.labelLarge.copy(color = MaterialTheme.colorScheme.onPrimaryContainer))
                Text(
                    modifier = Modifier.padding(top = 4.dp),
                    text = item.subTitle, style = MaterialTheme.typography.labelSmall.copy(color = MaterialTheme.colorScheme.onPrimaryContainer))
            }

            Text(text = item.value, style = MaterialTheme.typography.labelLarge.copy(color = MaterialTheme.colorScheme.onPrimaryContainer))


        }

        if (setDivider){
            Divider(thickness = 1.dp)
        }
    }

}

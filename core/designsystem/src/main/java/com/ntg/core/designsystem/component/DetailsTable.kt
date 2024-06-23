package com.ntg.core.designsystem.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun DetailsTable(
    modifier: Modifier = Modifier,
    items: List<Pair<String, String>>
){

    Column(modifier = modifier) {
        repeat(items.size){
            TableItem(item = items.get(it), showDivider = items.size != it)
        }
    }

}

@Composable
private fun TableItem(
    item: Pair<String, String>,
    showDivider: Boolean
){

    Column {
        Row(
            modifier = Modifier.padding(horizontal = 4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier
                    .weight(1f)
                    .padding(vertical = 16.dp),
                text = item.first, style = MaterialTheme.typography.bodyLarge.copy(color = MaterialTheme.colorScheme.secondary))

            Text(
                modifier = Modifier,
                text = item.first, style = MaterialTheme.typography.labelLarge.copy(color = MaterialTheme.colorScheme.onBackground))
        }

        if (showDivider){
            Divider(
                modifier = Modifier.fillMaxWidth(),
                color = MaterialTheme.colorScheme.surfaceVariant, thickness = 1.dp)
        }

    }


}

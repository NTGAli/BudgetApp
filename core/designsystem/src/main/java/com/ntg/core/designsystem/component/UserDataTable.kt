package com.ntg.core.designsystem.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.ntg.core.designsystem.model.UserDataTableItem

@Composable
fun UserDataTable(
  modifier: Modifier = Modifier,
  items: List<UserDataTableItem>,
  onClick: (Int) -> Unit,
) {

    Column(
        modifier = modifier
            .background(
                color = MaterialTheme.colorScheme.surface, shape = RoundedCornerShape(16.dp)
            )
            .clip(RoundedCornerShape(16.dp))
    ) {

        repeat(items.size) {
            Item(item = items[it], items.size - 1 != it) {
                onClick.invoke(it)
            }
        }

    }

}

@Composable
private fun Item(
    item: UserDataTableItem,
    setDivider: Boolean,
    onClick: (Int) -> Unit
) {
    var checked by remember { mutableStateOf(item.isChecked) }

    Column(
        modifier = Modifier
            .clickable {
                checked = !(checked ?: false)
                onClick.invoke(item.id)
            }
            .fillMaxWidth()

            .padding(horizontal = 16.dp)
    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {

            if (item.icon != null) {
                Icon(
                    modifier = Modifier
                        .padding(end = 16.dp)
                        .border(
                            width = 1.dp,
                            shape = RoundedCornerShape(8.dp),
                            color = MaterialTheme.colorScheme.surfaceVariant
                        )
                        .padding(12.dp),
                    painter = item.icon, contentDescription = null
                )
            }

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    modifier = Modifier.padding(top = 16.dp),
                    text = item.title,
                    style = MaterialTheme.typography.labelMedium.copy(color = MaterialTheme.colorScheme.outline)
                )

                Text(
                    modifier = Modifier
                        .padding(top = 8.dp, bottom = 16.dp),
                    text = item.subTitle,
                    style = MaterialTheme.typography.titleMedium.copy(color = MaterialTheme.colorScheme.onSurface)
                )
            }


            if (item.isChecked != null) {

                Switch(
                    checked = checked ?: false,
                    onCheckedChange = {
                        item.isChecked = it
                        checked = item.isChecked
                    }
                )
            }

        }

        if (setDivider) {
            Divider(thickness = 1.dp)
        }
    }

}

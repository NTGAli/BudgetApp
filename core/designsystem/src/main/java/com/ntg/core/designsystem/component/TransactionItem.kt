package com.ntg.core.designsystem.component

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
import com.ntg.core.designsystem.util.getTimeAndDate

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TransactionItem(
    modifier: Modifier = Modifier,
    title: String,
    time: Long,
    amount: Long,
    categoryIcon: Painter,
    isVisibleDivider: Boolean = true,
    onCLick:()-> Unit,
    onLongCLick:()-> Unit,
){

    var onLong by remember {
        mutableStateOf(false)
    }

    Box(modifier = modifier

        .clip(RoundedCornerShape(8.dp))
        .border(width = 1.dp, color = if (onLong) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.background, shape = RoundedCornerShape(8.dp))

        .combinedClickable(
            onClick = {
                onCLick.invoke()
            },
            onLongClick = {
                onLong = !onLong
                onLongCLick.invoke()
            },
        )
        .padding(horizontal = 4.dp)


    ){
        Row(
            modifier = Modifier
                .padding(vertical = 16.dp)
        ) {
            Icon(
                modifier = Modifier
                    .background(color = MaterialTheme.colorScheme.surface, shape = CircleShape)
                    .padding(12.dp),
                painter = categoryIcon, contentDescription = "category icon")


            Column(
                modifier = Modifier
                    .padding(start = 16.dp)
                    .weight(1f)
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleSmall.copy(color = MaterialTheme.colorScheme.onBackground)
                )

                Row(
                    modifier = Modifier.padding(top = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = getTimeAndDate(time).first,
                        style = MaterialTheme.typography.labelSmall.copy(MaterialTheme.colorScheme.outline)
                    )

                    Box(
                        modifier = Modifier
                            .padding(horizontal = 8.dp)
                            .background(
                                color = MaterialTheme.colorScheme.outline,
                                shape = CircleShape
                            )
                            .size(4.dp)
                            .padding(horizontal = 8.dp)
                    )

                    Text(
                        text = getTimeAndDate(time).second,
                        style = MaterialTheme.typography.labelSmall.copy(MaterialTheme.colorScheme.outline)
                    )
                }
            }

            Text(text = amount.toString(), style = MaterialTheme.typography.titleMedium.copy(color = MaterialTheme.colorScheme.error))

        }

        if (isVisibleDivider){
            Divider(color = MaterialTheme.colorScheme.surface, thickness = 1.dp, modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter))
        }
    }


}

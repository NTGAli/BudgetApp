package com.ntg.core.designsystem.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.ntg.samples.apps.budgetapp.core.designsystem.R

@Composable
fun UserData(
    modifier: Modifier = Modifier,
    image: Painter? = null,
    email: String,
    name: String,
    state: Boolean,
    onClick:()-> Unit = {}
){

    val finalText = if (state) "Pro" else "Free"
    val textColor = if (state) MaterialTheme.colorScheme.tertiary else MaterialTheme.colorScheme.error
    val backColor = if (state) MaterialTheme.colorScheme.tertiaryContainer else MaterialTheme.colorScheme.errorContainer

    Row(modifier = modifier
        .clickable {
            onClick.invoke()
        }
        .padding(horizontal = 24.dp, vertical = 14.dp)
        , verticalAlignment = Alignment.CenterVertically) {
        Image(
            modifier = Modifier.clip(RoundedCornerShape(64.dp)).size(64.dp),
            painter = image ?: painterResource(id = R.drawable.user_circle_big), contentDescription = null)

        Column(
            modifier = Modifier
                .padding(start = 16.dp)
                .weight(1f)
        ) {
            Text(
                text = email,
                style = MaterialTheme.typography.titleMedium.copy(color = MaterialTheme.colorScheme.onBackground)
            )

            Text(
                modifier = Modifier.padding(top = 4.dp),
                text = name,
                style = MaterialTheme.typography.labelMedium.copy(color = MaterialTheme.colorScheme.onBackground)
            )
        }

        Text(
            modifier = Modifier
                .background(color = backColor, shape = RoundedCornerShape(8.dp))
                .padding(horizontal = 8.dp, vertical = 4.dp)
            ,
            text = finalText, style = MaterialTheme.typography.labelMedium.copy(color = textColor))

    }


}

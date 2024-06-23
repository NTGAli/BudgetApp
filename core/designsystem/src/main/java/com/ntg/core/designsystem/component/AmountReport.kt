package com.ntg.core.designsystem.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.ntg.samples.apps.budgetapp.core.designsystem.R

@Composable
fun AmountReport(
    modifier: Modifier = Modifier,
    outcome: Long?,
    income: Long?
){


    Row(
        modifier = modifier
            .height(IntrinsicSize.Min)
            .wrapContentHeight().fillMaxWidth()
            .background(color = MaterialTheme.colorScheme.surface, shape = RoundedCornerShape(16.dp))
    ) {

        Column(
            modifier = Modifier
                .padding(vertical = 16.dp, horizontal = 8.dp)
                .weight(1f)
        ) {
            Text(text = stringResource(id = R.string.income), style = MaterialTheme.typography.labelSmall.copy(color = MaterialTheme.colorScheme.onSurfaceVariant))
            Text(
                modifier = Modifier.padding(top = 4.dp),
                text = income?.toString() ?: "---", style = MaterialTheme.typography.labelLarge.copy(color = MaterialTheme.colorScheme.onSurfaceVariant))
        }


        Divider(modifier = Modifier
            .fillMaxHeight()
            .padding(horizontal = 12.dp)
            .width(1.dp)
            .padding(vertical = 8.dp)
            .background(MaterialTheme.colorScheme.onSurfaceVariant)

        )

        Column(
            modifier = Modifier
                .padding(vertical = 16.dp, horizontal = 8.dp)
                .weight(1f)
        ) {
            Text(text = stringResource(id = R.string.outcome), style = MaterialTheme.typography.labelSmall.copy(color = MaterialTheme.colorScheme.onSurfaceVariant))
            Text(
                modifier = Modifier.padding(top = 4.dp),
                text = outcome?.toString() ?: "---", style = MaterialTheme.typography.labelLarge.copy(color = MaterialTheme.colorScheme.onSurfaceVariant))
        }


    }

}

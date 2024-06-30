package com.ntg.core.designsystem.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.ntg.core.designsystem.icon.BudgetIcon
import com.ntg.samples.apps.budgetapp.core.designsystem.R

@Composable
fun SheetSearch(
  modifier: Modifier = Modifier,
  query: MutableState<String>,
) {

  Row(
    modifier = modifier
      .fillMaxWidth()
      .clip(shape = RoundedCornerShape(16.dp))
      .background(shape = RoundedCornerShape(16.dp), color = MaterialTheme.colorScheme.surfaceVariant)
      .padding(8.dp),
    verticalAlignment = Alignment.CenterVertically
  ) {

    Icon(
      modifier = Modifier.size(16.dp),
      painter = painterResource(id = BudgetIcon.search), contentDescription = null)


    Box(
      modifier = Modifier.padding(start = 8.dp)
        .weight(1f),
    ) {

      if (query.value.isEmpty()) {
        Text(
          modifier = Modifier.align(Alignment.CenterStart),
          text = stringResource(id = R.string.search),
          style = MaterialTheme.typography.labelLarge.copy(color = MaterialTheme.colorScheme.outline),
        )
      }
      BasicTextField(
        modifier = Modifier
          .fillMaxWidth()
          .align(Alignment.CenterStart),
        value = query.value,
        onValueChange = {
          query.value = it
        },
        textStyle = MaterialTheme.typography.labelLarge.copy(color = MaterialTheme.colorScheme.onSurface),
        cursorBrush = SolidColor(MaterialTheme.colorScheme.primary)
      )
    }

  }
}

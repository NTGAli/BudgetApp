package com.ntg.core.designsystem.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.ntg.samples.apps.budgetapp.core.designsystem.R

@Composable
fun ButtonBottomBar(
  modifier: Modifier = Modifier,
  title: String = stringResource(id = R.string.submit),
  enable: Boolean = true,
  loading: Boolean = false,
  onClick: () -> Unit,
){

  Column {
    HorizontalDivider(color = MaterialTheme.colorScheme.surfaceVariant, thickness = 1.dp)
    Button(
      modifier = Modifier.fillMaxWidth()
        .padding(horizontal = 32.dp, vertical = 16.dp),
      size = ButtonSize.LG,
      text = title,
      enable = enable,
      loading = loading,
    ){
      onClick()
    }
  }

}

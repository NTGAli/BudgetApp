package com.ntg.feature.setup

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.ntg.core.designsystem.component.Button
import com.ntg.core.designsystem.component.ButtonSize
import com.ntg.core.designsystem.component.TextField

@Composable
fun SetupRoute() {
  CardViewScreen()
}

@Composable
fun SetupScreen() {

  val country = remember {
    mutableStateOf("")
  }

  val format = remember {
    mutableStateOf("")
  }

  Column {

    Text(
        modifier = Modifier.padding(32.dp),
        text = stringResource(id = R.string.lets_setup), style = MaterialTheme.typography.displaySmall,
    )

    TextField(
      modifier = Modifier
          .fillMaxWidth()
          .padding(horizontal = 32.dp),
      value = country, label = stringResource(id = R.string.country),
    )

    TextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 32.dp)
            .padding(top = 8.dp),
        value = format, label = stringResource(id = R.string.format),
    )

    Button(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 24.dp)
            .padding(horizontal = 32.dp),
        text = stringResource(id = R.string.next), size = ButtonSize.XL,
    )


  }


}

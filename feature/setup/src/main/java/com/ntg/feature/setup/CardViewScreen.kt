package com.ntg.feature.setup

import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.ntg.core.designsystem.component.ButtonType
import com.ntg.core.designsystem.component.Card
import com.ntg.core.designsystem.component.CardStyles
import com.ntg.core.designsystem.component.MessageBox
import com.ntg.core.designsystem.component.TextDivider
import com.ntg.core.designsystem.component.TextField
import com.ntg.core.designsystem.icon.BudgetIcon


@Composable
fun CardView_Route() {
  CardViewScreen()
}

@Composable
fun CardViewScreen() {

  val name = remember {
    mutableStateOf("")
  }

  val card = remember {
    mutableStateOf("")
  }

  Scaffold {

    Column(
      modifier = Modifier
        .padding(it)
        .verticalScroll(rememberScrollState()),
    ) {

      Text(
        modifier = Modifier.padding(32.dp),
        text = stringResource(id = R.string.your_card),
        style = MaterialTheme.typography.displaySmall.copy(MaterialTheme.colorScheme.onBackground),
      )


      Card(
        modifier = Modifier.padding(horizontal = 32.dp),
        card = ImageVector.vectorResource(BudgetIcon.sampleCard),
        cardNumber = card.value,
        name = name.value,
        fullView = false,
      )

      TextDivider(
        modifier = Modifier
          .padding(top = 24.dp)
          .padding(horizontal = 32.dp),
        title = stringResource(id = R.string.card_style),
      )

      CardStyles(
        modifier = Modifier
          .padding(horizontal = 32.dp)
          .padding(top = 16.dp),
        samples = listOf(BudgetIcon.sampleCard, BudgetIcon.sampleCard, BudgetIcon.sampleCard),
      ) {

      }

      TextDivider(
        modifier = Modifier
          .padding(top = 24.dp)
          .padding(horizontal = 32.dp),
        title = stringResource(id = R.string.card_info),
      )

      TextField(
        modifier = Modifier
          .padding(top = 16.dp)
          .padding(horizontal = 32.dp),
        value = card, label = stringResource(id = R.string.card_number),
      )

      TextField(
        modifier = Modifier
          .padding(top = 8.dp)
          .padding(horizontal = 32.dp),
        value = name, label = stringResource(id = R.string.name),
      )

      MessageBox(
        modifier = Modifier
          .padding(horizontal = 32.dp)
          .padding(top = 24.dp),
        text = stringResource(id = R.string.only_for_display), type = ButtonType.Success)

    }

  }

}

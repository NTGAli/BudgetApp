package com.ntg.feature.setup

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ntg.core.commom.util.generateUniqueFiveDigitId
import com.ntg.core.designsystem.component.ButtonBottomBar
import com.ntg.core.designsystem.component.ButtonType
import com.ntg.core.designsystem.component.Card
import com.ntg.core.designsystem.component.CardStyles
import com.ntg.core.designsystem.component.MessageBox
import com.ntg.core.designsystem.component.TextDivider
import com.ntg.core.designsystem.component.TextField
import com.ntg.core.designsystem.icon.BudgetCardStyles
import com.ntg.core.designsystem.icon.BudgetIcon
import com.ntg.core.model.Card


@Composable
fun CardViewRoute(
  setupViewModel: SetupViewModel = hiltViewModel(),
  navigateToBalanceCheckScreen: () -> Unit,
) {
  CardViewScreen{
    setupViewModel.setupCard = it
    Log.d("CardViewScreenCardViewScreen", "${setupViewModel.setupCard}")
    navigateToBalanceCheckScreen()
  }
}

@Composable
fun CardViewScreen(
  saveTempCard:(Card) -> Unit,
) {

  val name = remember {
    mutableStateOf("")
  }

  val card = remember {
    mutableStateOf("")
  }

  var cardStyleSelected by remember {
    mutableIntStateOf(-1)
  }

  Scaffold(
    bottomBar = {
      ButtonBottomBar {
        saveTempCard(
          Card(
            id = generateUniqueFiveDigitId(),
            number = card.value,
            name = name.value,
            dateCreated = System.currentTimeMillis().toString(),
            cardStyleId = cardStyleSelected
          )
        )
      }
    },
  ) {

    Column(
      modifier = Modifier
        .padding(it)
        .verticalScroll(rememberScrollState())
        .padding(bottom = 64.dp),
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
        samples = BudgetCardStyles,
      ) {
        cardStyleSelected = it
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

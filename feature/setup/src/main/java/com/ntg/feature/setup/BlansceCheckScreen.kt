package com.ntg.feature.setup

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ntg.core.commom.util.generateUniqueFiveDigitId
import com.ntg.core.designsystem.component.ButtonBottomBar
import com.ntg.core.designsystem.component.TextField
import com.ntg.core.model.Transaction


@Composable
fun BalanceCheckRoute(
  setupViewModel: SetupViewModel = hiltViewModel(),
  navigateToCurrencyFormatScreen: () -> Unit,
  navigateToHome: () -> Unit,
) {

  Log.d("CardViewScreenCardViewScreen", "${setupViewModel.setupCard}")

  BalanceCheckScreen(navigateToCurrencyFormatScreen) { balance ->
    if (setupViewModel.setupCard != null){
      setupViewModel.insertNewCard(setupViewModel.setupCard!!)
      setupViewModel.upsertTransaction(
        Transaction(
          id = generateUniqueFiveDigitId(),
          amount = balance,
          cardId =  setupViewModel.setupCard?.id!!,
          type = 0,
          timeStamp = System.currentTimeMillis().toString(),
          catId = "-"
        )
      )
    }
    navigateToHome()
  }
}

@Composable
fun BalanceCheckScreen(
  navigateToCurrencyFormatScreen: () -> Unit,
  saveBalance: (Long) -> Unit,
) {


  val currencyFormat = remember {
    mutableStateOf("")
  }

  val balance = remember {
    mutableStateOf("")
  }


  Scaffold(
    bottomBar = {
      ButtonBottomBar {
        saveBalance(
          balance.value.toLong()
        )
      }
    },
  ) {
    Column(modifier = Modifier.padding(it)) {


      Text(
        modifier = Modifier.padding(top = 32.dp, start = 32.dp),
        text = stringResource(id = R.string.balance_check), style = MaterialTheme.typography.displaySmall,
      )

      Text(
        modifier = Modifier.padding(start = 32.dp, top = 16.dp),
        text = stringResource(id = R.string.balance_check_question),
        style = MaterialTheme.typography.bodyMedium.copy(MaterialTheme.colorScheme.onSurface),
      )

      TextField(
        modifier = Modifier
          .fillMaxWidth()
          .padding(horizontal = 32.dp)
          .padding(top = 32.dp),
        value = currencyFormat, label = stringResource(id = R.string.currency_format),
        readOnly = true,
      ) {
        navigateToCurrencyFormatScreen()
      }

      TextField(
        modifier = Modifier
          .fillMaxWidth()
          .padding(horizontal = 32.dp)
          .padding(top = 8.dp),
        value = balance, label = stringResource(id = R.string.balance),
      )


    }
  }


}

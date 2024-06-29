package com.ntg.core.designsystem.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp

@Composable
fun AutoCompleteDropdown(
  modifier: Modifier,
  query: MutableState<String>,
  list: List<String>,
  onClick:(String) -> Unit
){
  AnimatedVisibility(visible = list.any { it.lowercase().contains(query.value.lowercase()) && it.lowercase() != query.value.lowercase() } && query.value.isNotEmpty()) {
    androidx.compose.material3.Card(
      modifier = modifier,
      shape = RoundedCornerShape(16.dp)) {
      LazyColumn {
        items(
          list.filter { it.contains(query.value) }
        ){
          Item(text = it) {
            onClick(it)
          }

        }
      }
    }

  }
}

@Composable
private fun Item(text: String, onClick:()-> Unit){

  Row(
    modifier = Modifier
      .fillMaxWidth()
      .clip(RoundedCornerShape(8.dp))
      .clickable {
        onClick.invoke()
      }
  ) {
    Text(
      modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp),
      text = text, style = MaterialTheme.typography.titleMedium.copy(MaterialTheme.colorScheme.onSurfaceVariant))
  }

}

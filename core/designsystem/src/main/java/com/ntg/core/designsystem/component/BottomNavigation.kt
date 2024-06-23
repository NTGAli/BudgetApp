package com.ntg.core.designsystem.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.ntg.core.designsystem.model.NavigationItem
import com.ntg.samples.apps.budgetapp.core.designsystem.R

@Composable
fun BottomNavigation(
  modifier: Modifier = Modifier,
  items: List<NavigationItem>,
  onCLick: (Int) -> Unit,
) {


    val firstItem = items[0]
    val secondItem = items[1]

  Column(
      modifier = modifier.padding(bottom = 24.dp).background(MaterialTheme.colorScheme.background)
  ) {
      Divider()
      Row(
          modifier = Modifier
              .fillMaxWidth(),
          verticalAlignment = Alignment.CenterVertically
      ) {

          Icon(
              modifier = Modifier
                  .padding(2.dp)
                  .clip(RoundedCornerShape(8.dp))
                  .clickable {
                      if (!firstItem.isSelected){
                          onCLick.invoke(firstItem.id)
                      }
                  }
                  .weight(1f)
                  .padding(vertical = 16.dp),
              painter = firstItem.painter, contentDescription = firstItem.title
          )


          Icon(
              modifier = Modifier
                  .padding(horizontal = 8.dp)
                  .clip(RoundedCornerShape(8.dp))
                  .background(
                      shape = RoundedCornerShape(8.dp),
                      color = MaterialTheme.colorScheme.primary
                  )
                  .clickable {
                      onCLick.invoke(-1)
                  }
                  .padding(horizontal = 24.dp, vertical = 4.dp)
              ,
              painter = painterResource(id = R.drawable.arrow_sort),
              contentDescription = "Transaction",
              tint = MaterialTheme.colorScheme.onPrimary
          )

          Icon(
              modifier = Modifier
                  .padding(2.dp)
                  .clip(RoundedCornerShape(8.dp))
                  .clickable {
                      if (!secondItem.isSelected){
                          onCLick.invoke(secondItem.id)
                      }
                  }
                  .weight(1f)
                  .padding(vertical = 16.dp),
              painter = secondItem.painter, contentDescription = secondItem.title
          )
      }
  }
}

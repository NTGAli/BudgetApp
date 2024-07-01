package com.ntg.core.designsystem.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.ntg.core.designsystem.icon.BudgetIcon
import com.ntg.core.designsystem.model.CardStyle

@Composable
fun CardStyles(
  modifier: Modifier = Modifier,
  samples: List<CardStyle>,
  onClick: (Int) -> Unit,
) {

  var selectedCard by remember {
    mutableIntStateOf(-1)
  }

  Row(modifier) {

    Item(
      isSelected = samples[0].id == selectedCard,
      res = samples[0].res!!) {
      onClick(samples[0].id)
      selectedCard = samples[0].id
    }
    Item(
      isSelected = samples[1].id == selectedCard,
      res = samples[1].res!!) {
      onClick(samples[1].id)
      selectedCard = samples[1].id
    }
    Item(
      isSelected = samples[2].id == selectedCard,
      res = samples[2].res!!) {
      onClick(samples[2].id)
      selectedCard = samples[2].id
    }
    More {
      onClick(-1)
      selectedCard = -1
    }

  }


}

@Composable
private fun RowScope.Item(
  isSelected: Boolean = false,
  res: Int, onClick: (Int) -> Unit) {

  Image(
    modifier = Modifier
      .weight(1f)
      .padding(horizontal = 4.dp)
      .aspectRatio(1f)
      .clip(RoundedCornerShape(8.dp))
      .apply {
        if (isSelected)
          this.border(5.dp, color = MaterialTheme.colorScheme.primary, shape = RoundedCornerShape(8.dp))
      }
      .then(if (isSelected)
        Modifier.border(2.dp, color = MaterialTheme.colorScheme.primary, shape = RoundedCornerShape(8.dp)) else Modifier)
      .clickable {
        onClick(res)
      },
    painter = painterResource(id = res), contentDescription = null, contentScale = ContentScale.Crop,
  )
}


@Composable
private fun RowScope.More(
  onClick: () -> Unit,
) {
  Box(
    modifier = Modifier
      .weight(1f)
      .padding(horizontal = 4.dp)
      .aspectRatio(1f)
      .clip(RoundedCornerShape(8.dp))
      .border(width = 2.dp, color = MaterialTheme.colorScheme.primary, shape = RoundedCornerShape(8.dp))
      .clickable {
        onClick()
      },
    contentAlignment = Alignment.Center,
  ) {

    Icon(painter = painterResource(id = BudgetIcon.ArrowRight), contentDescription = "more")

  }
}

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.ntg.core.designsystem.icon.BudgetIcon

@Composable
fun CardStyles(
  modifier: Modifier = Modifier,
  samples: List<Int>,
  onClick: (Int) -> Unit,
) {

  Row(modifier) {

    Item(res = samples[0]) {
      onClick(it)
    }
    Item(res = samples[1]) {
      onClick(it)
    }
    Item(res = samples[2]) {
      onClick(it)
    }
    More {
      onClick(-1)
    }

  }


}

@Composable
private fun RowScope.Item(res: Int, onClick: (Int) -> Unit) {
  Image(
    modifier = Modifier
      .weight(1f)
      .padding(horizontal = 4.dp)
      .aspectRatio(1f)
      .clip(RoundedCornerShape(8.dp))
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

package com.ntg.core.designsystem.component

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.VectorConverter
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInParent
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.ntg.core.designsystem.model.SwitchTextColor
import kotlinx.coroutines.launch

@Composable
fun SwitchText(
    modifier: Modifier = Modifier,
    firstText: String,
    secondText: String,
    color: SwitchTextColor = defaultSwitchTextColor()
) {


    val scope = rememberCoroutineScope()

    var width by remember {
        mutableIntStateOf(0)
    }

    var itemSelected by rememberSaveable {
        mutableIntStateOf(1)
    }


    val offsetSelected = remember { Animatable(Offset.Zero, Offset.VectorConverter) }

    Box(
        modifier = modifier
            .height(IntrinsicSize.Min)
            .onGloballyPositioned {
                width = it.size.width / 2
            }
            .border(
                color = color.borderColor,
                shape = RoundedCornerShape(ROUND_CORNER.dp),
                width = WIDTH_SHAPE.dp
            )
            .wrapContentHeight()
    ) {

        Box(
            modifier = Modifier
                .fillMaxHeight()
                .width(with(LocalDensity.current) { width.toDp() })
                .padding(8.dp)
                .offset(
                    x = with(LocalDensity.current) { offsetSelected.value.x.toDp() }
                )
                .background(
                    shape = RoundedCornerShape(SELECTOR_CORNER.dp),
                    color = if (itemSelected == 1) color.firstBackColor else color.secondBackColor
                )


        )

        Row {

            ItemSelector(
                text = firstText,
                color = color,
                isFirst = true,
                isSelected = itemSelected == 1
            ) {
                scope.launch {
                    offsetSelected.animateTo(Offset(x = it, y = offsetSelected.value.y))

                }
                itemSelected = 1
            }


            ItemSelector(
                text = secondText,
                color = color,
                isSelected = itemSelected == 2
            ) {
                scope.launch {
                    offsetSelected.animateTo(Offset(x = it, y = offsetSelected.value.y))
                }
                itemSelected = 2
            }


        }


    }

}


@Composable
private fun RowScope.ItemSelector(
    text: String,
    color: SwitchTextColor,
    isSelected: Boolean,
    isFirst: Boolean = false,
    onClick: (Float) -> Unit
) {

    var itemOffset by remember {
        mutableStateOf(0f)
    }

    Text(
        modifier = Modifier
            .onGloballyPositioned {
                itemOffset = it.positionInParent().x
            }
            .clip(RoundedCornerShape(8.dp))
            .clickable(
                interactionSource = MutableInteractionSource(),
                indication = null,
                onClick = { onClick.invoke(itemOffset) })
            .weight(1f)
            .padding(vertical = 16.dp),
        text = text,
        style = MaterialTheme.typography.labelMedium.copy(
            color = if (isSelected)
                if (isFirst) color.firstColor else color.secondColor
            else color.defaultColor
        ),
        textAlign = TextAlign.Center
    )
}


@Composable
private fun defaultSwitchTextColor(): SwitchTextColor {
    return SwitchTextColor(
        defaultColor = MaterialTheme.colorScheme.secondary,
        firstColor = MaterialTheme.colorScheme.error,
        secondColor = MaterialTheme.colorScheme.tertiary,
        firstBackColor = MaterialTheme.colorScheme.errorContainer,
        secondBackColor = MaterialTheme.colorScheme.tertiaryContainer,
        borderColor = MaterialTheme.colorScheme.surfaceVariant
    )
}


//settings
private const val ROUND_CORNER = 12
private const val SELECTOR_CORNER = 8
private const val WIDTH_SHAPE = 2

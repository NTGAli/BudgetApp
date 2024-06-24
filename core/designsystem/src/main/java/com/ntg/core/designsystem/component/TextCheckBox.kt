package com.ntg.core.designsystem.component

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import com.ntg.samples.apps.budgetapp.core.designsystem.R

@Composable
fun TextCheckBox(
    modifier: Modifier = Modifier,
    title: String,
    isChecked: MutableState<Boolean>,
    onClick:() -> Unit
){


    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .heightIn(24.dp)
            .toggleable(
                value = isChecked.value,
                role = Role.Checkbox,
                onValueChange = {
                    onClick.invoke()
                }
            )
            .fillMaxWidth()
            .clickable {
                onClick.invoke()
            }
            .padding(vertical = 16.dp, horizontal = 8.dp)
        ,
    ) {

        RoundedCornerCheckbox(isChecked.value)

        Text(
            modifier = Modifier.padding(start = 8.dp),
            text = title,
            style = MaterialTheme.typography.labelMedium.copy(color = MaterialTheme.colorScheme.secondary))
    }

}



@Composable
fun TextRadio(
    modifier: Modifier = Modifier,
    title: String,
    isChecked: MutableState<Boolean>,
    onClick:() -> Unit
){


    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .heightIn(24.dp)
            .toggleable(
                value = isChecked.value,
                role = Role.Checkbox,
                onValueChange = {
                    onClick.invoke()
                }
            )
            .fillMaxWidth()
            .clickable {
                onClick.invoke()
            }
            .padding(vertical = 16.dp, horizontal = 8.dp)
        ,
    ) {

        RoundedCornerCheckbox(isChecked.value, radius = 12)

        Text(
            modifier = Modifier.padding(start = 8.dp),
            text = title,
            style = MaterialTheme.typography.labelMedium.copy(color = MaterialTheme.colorScheme.secondary))
    }

}


@Composable
fun RoundedCornerCheckbox(
    isChecked: Boolean,
    size: Float = 24f,
    radius: Int = 4,
    checkedColor: Color = MaterialTheme.colorScheme.primary,
    uncheckedColor: Color = MaterialTheme.colorScheme.background,
) {
    val checkboxColor: Color by animateColorAsState(if (isChecked) checkedColor else uncheckedColor,
        label = ""
    )
    val density = LocalDensity.current
    val duration = 200




        Box(
            modifier = Modifier
                .size(size.dp)
                .background(color = checkboxColor, shape = RoundedCornerShape(radius.dp))
                .border(width = 1.5.dp, color = if (isChecked) checkedColor else MaterialTheme.colorScheme.outline, shape = RoundedCornerShape(radius.dp)),
            contentAlignment = Alignment.Center
        ) {
            androidx.compose.animation.AnimatedVisibility(
                visible = isChecked,
                enter = slideInHorizontally(animationSpec = tween(duration)) {
                    with(density) { (size * -0.5).dp.roundToPx() }
                } + expandHorizontally(
                    expandFrom = Alignment.Start,
                    animationSpec = tween(duration)
                ),
                exit = fadeOut()
            ) {
                Icon(
                    painterResource(id = R.drawable.check_small),
                    contentDescription = null,
                    tint = uncheckedColor
                )
            }
        }
}

package com.ntg.core.designsystem.model

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

data class SwitchTextColor(
    val defaultColor: Color,
    val firstColor: Color,
    val secondColor: Color,
    val firstBackColor: Color,
    val secondBackColor: Color,
    val borderColor: Color,
)

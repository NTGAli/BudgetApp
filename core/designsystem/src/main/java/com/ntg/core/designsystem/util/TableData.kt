package com.ntg.core.designsystem.util

import androidx.compose.ui.graphics.painter.Painter

data class TableData(
    val id: Int,
    val catImage: Painter,
    val title: String,
    val subTitle: String,
    val value: String
)

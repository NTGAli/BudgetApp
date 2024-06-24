package com.ntg.core.designsystem.model

import androidx.compose.ui.graphics.painter.Painter

data class UserDataTableItem(
    val id: Int,
    val title: String,
    val subTitle: String,
    val icon: Painter? = null,
    var isChecked: Boolean? = null
)

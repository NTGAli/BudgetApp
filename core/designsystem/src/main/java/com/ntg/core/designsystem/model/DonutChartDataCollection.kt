package com.ntg.core.designsystem.model

data class DonutChartDataCollection(
    var items: List<DonutChartData>
) {
    var totalAmount: Float = items.sumOf { it.amount.toDouble() }.toFloat()
        private set
}

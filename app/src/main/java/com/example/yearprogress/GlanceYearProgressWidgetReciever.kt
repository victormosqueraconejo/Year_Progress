package com.example.yearprogress

import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.GlanceAppWidgetReceiver

class GlanceYearProgressWidgetReciever : GlanceAppWidgetReceiver() {

    override val glanceAppWidget: GlanceAppWidget = YearProgressWidget()

}
package com.example.yearprogress

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF


fun CreateCircularProgressBitmap (
    sizePx : Int,
    progress : Float,
    trackColor: Int,
    progressColor: Int,
    strokePx: Float
) : Bitmap {

    val bitmap = Bitmap.createBitmap(sizePx,sizePx,Bitmap.Config.ARGB_8888)
    val canvas = Canvas(bitmap)

    val paintTrack = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.STROKE
        strokeWidth = strokePx
        color = trackColor
        strokeCap = Paint.Cap.ROUND

    }


    val paintProgress = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.STROKE
        strokeWidth = strokePx
        color = progressColor
        strokeCap = Paint.Cap.ROUND
    }


    val pad = strokePx / 2f + 2f
    val rect = RectF( pad, pad, sizePx - pad , sizePx - pad)


    canvas.drawArc(rect, 0f, 360f, false, paintTrack)
    canvas.drawArc(rect, -90f, 360f * progress.coerceIn(0f, 1f), false, paintProgress)

 return bitmap

}
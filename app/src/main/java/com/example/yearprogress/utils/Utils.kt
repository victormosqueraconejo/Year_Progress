package com.example.yearprogress.utils

import android.graphics.Bitmap
import com.example.yearprogress.CreateCircularProgressBitmap

object Utils {


    fun getCircularTrackMaskBitmap(): Bitmap {
        return CreateCircularProgressBitmap(
            sizePx = 220,
            progress = 1f,
            trackColor = android.graphics.Color.WHITE,
            progressColor = android.graphics.Color.TRANSPARENT,
            strokePx = 18f
        )
    }

    fun getCircularProgressMaskBitmap(progress: Float): Bitmap {
        return CreateCircularProgressBitmap(
            sizePx = 220,
            progress = progress,
            trackColor = android.graphics.Color.TRANSPARENT,
            progressColor = android.graphics.Color.WHITE,
            strokePx = 18f
        )
    }



}
package com.example.yearprogress.utils

import android.content.Context
import android.graphics.Bitmap
import androidx.datastore.preferences.preferencesDataStore
import com.example.yearprogress.CreateCircularProgressBitmap

object Utils {


    val Context.dataStore by preferencesDataStore(name = "setting")


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

    fun formatPorcentaje(number : Float) : String {
        return String.format("%.2f", number)
    }



}
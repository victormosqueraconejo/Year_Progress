package com.example.yearprogress.repository

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.datastore.preferences.preferencesDataStoreFile
import com.example.yearprogress.utils.Utils.dataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


class WidgetSelecionRepository (
    private val context : Context
) {
    private val KEY  = intPreferencesKey("widget_variant")

    val selectedVariantFlow : Flow<Int> = context.dataStore.data.map { preferences -> preferences[KEY]?: 1 }

    suspend fun setSelectedVariant (id: Int) {
         context.dataStore.edit { preferences -> preferences[KEY] = id }
    }



}
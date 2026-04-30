package com.example.yearprogress.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.yearprogress.repository.WidgetSelecionRepository
import kotlinx.coroutines.launch

class WidgetSelectionViewModel(
    private val repo : WidgetSelecionRepository
) : ViewModel() {

    val selectionVariant = repo.selectedVariantFlow

    fun selectVariant( id : Int) {
        viewModelScope.launch {
            repo.setSelectedVariant(id)
        }
    }



}
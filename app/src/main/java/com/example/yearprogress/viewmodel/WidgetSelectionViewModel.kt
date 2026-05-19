package com.example.yearprogress.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.yearprogress.repository.WidgetSelecionRepository
import kotlinx.coroutines.launch

class WidgetSelectionViewModel(
    private val repo : WidgetSelecionRepository
) : ViewModel() {

    val selectionVariant = repo.selectedVariantFlow

    suspend fun selectVariant( id : Int) {
        repo.setSelectedVariant(id)
    }

    class Factory(private val repo: WidgetSelecionRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(WidgetSelectionViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return WidgetSelectionViewModel(repo) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}
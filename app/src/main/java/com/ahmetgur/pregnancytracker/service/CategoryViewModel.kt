package com.ahmetgur.pregnancytracker.service

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class CategoryViewModel : ViewModel() {
    private val _categorieState = mutableStateOf(DiscoverState())
    val categoriesState: State<DiscoverState> = _categorieState

    init {
        fetchCategories()
    }

    private fun fetchCategories() {
        viewModelScope.launch {
            try {
                val response = discoverService.getCategories()
                _categorieState.value = _categorieState.value.copy(
                    list = response.categories,
                    loading = false,
                    error = null
                )
            } catch (e: Exception) {
                _categorieState.value = _categorieState.value.copy(
                    loading = false,
                    error = e.message
                )
            }
        }
    }

    data class DiscoverState(
        val loading: Boolean = false,
        val list: List<Category> = emptyList(),
        val error: String? = null
    )

}
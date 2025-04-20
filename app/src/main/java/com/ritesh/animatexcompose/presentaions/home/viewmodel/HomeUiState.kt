package com.ritesh.animatexcompose.presentaions.home.viewmodel

import com.ritesh.animatexcompose.domain.model.AnimationItem

sealed class HomeUiState {
    object Loading : HomeUiState()
    data class Success(val animations: List<AnimationItem>) : HomeUiState()
    data class Error(val message: String) : HomeUiState()
}

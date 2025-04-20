package com.ritesh.animatexcompose.presentaions.details.viewmodel

import com.ritesh.animatexcompose.domain.model.AnimationItem

sealed class DetailsUiState {
    object Loading : DetailsUiState()
    data class Success(val animation: AnimationItem) : DetailsUiState()
    data class Error(val message: String) : DetailsUiState()
}

package com.ritesh.animatexcompose.presentaions.details.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ritesh.animatexcompose.domain.repository.AnimationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val repository: AnimationRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _uiState = MutableStateFlow<DetailsUiState>(DetailsUiState.Loading)
    val uiState: StateFlow<DetailsUiState> = _uiState.asStateFlow()

    init {
        savedStateHandle.get<Int>("animationId")?.let { id ->
            loadAnimation(id)
        } ?: run {
            _uiState.value = DetailsUiState.Error("Animation ID not found")
        }
    }

    private fun loadAnimation(id: Int) {
        viewModelScope.launch {
            repository.getAnimationById(id).collect { animation ->
                if (animation != null) {
                    _uiState.value = DetailsUiState.Success(animation)
                } else {
                    _uiState.value = DetailsUiState.Error("Animation not found")
                }
            }
        }
    }
}
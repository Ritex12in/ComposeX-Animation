package com.ritesh.animatexcompose.presentaions.navigation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ritesh.animatexcompose.data.datastore.DataStoreManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NavigationViewModel @Inject constructor(
    private val dataStore: DataStoreManager
) : ViewModel() {

    val isFirstLaunch = dataStore.isFirstLaunch.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = true
    )

    fun setFirstLaunchCompleted() {
        viewModelScope.launch {
            dataStore.saveIsFirstLaunch(false)
        }
    }
}
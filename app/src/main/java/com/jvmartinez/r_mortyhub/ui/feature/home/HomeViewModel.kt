package com.jvmartinez.r_mortyhub.ui.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jvmartinez.r_mortyhub.core.api.DataResult
import com.jvmartinez.r_mortyhub.core.useCase.ActionGetCharactersUseCase
import com.jvmartinez.r_mortyhub.ui.base.StatusData
import com.jvmartinez.r_mortyhub.ui.feature.home.data.HomeUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getCharactersUseCase: ActionGetCharactersUseCase
) : ViewModel() {

    private val _homeState = MutableStateFlow<StatusData<HomeUiState>>(StatusData.Empty)
    val homeState = _homeState.asStateFlow()

    fun getCharacters(page: Int = 1) {
        viewModelScope.launch(Dispatchers.IO) {
            _homeState.value = StatusData.Loading
            delay(500L)
            try {
                when (val result = getCharactersUseCase(page)) {
                    is DataResult.Success -> {
                        _homeState.value = StatusData.Success(
                            HomeUiState(characters = result.data.results)
                        )
                    }
                    is DataResult.Exception -> {
                        _homeState.value = StatusData.Error(result.throwable.message ?: "Unknown error")
                    }
                }
            } catch (e: Exception) {
                _homeState.value = StatusData.Error(e.message ?: "Unknown error")
            }
        }
    }
}

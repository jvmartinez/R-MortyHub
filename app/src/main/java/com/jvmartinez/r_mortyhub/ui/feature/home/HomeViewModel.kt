package com.jvmartinez.r_mortyhub.ui.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jvmartinez.r_mortyhub.core.api.DataResult
import com.jvmartinez.r_mortyhub.core.data.model.Character
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

    private val _homeState = MutableStateFlow<StatusData<HomeUiState>>(StatusData.Loading)
    val homeState = _homeState.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    private val _characters = mutableListOf<Character>()
    var totalPage: Int = 1


    fun getCharacters(page: Int = 1) {
        if (_isLoading.value) return
        _isLoading.value = true

        viewModelScope.launch(Dispatchers.IO) {
            if (page == 1) {
                _homeState.value = StatusData.Loading
            }
            delay(500L)
            try {
                if (validCallApi(page)) {
                    when (val result = getCharactersUseCase(page)) {
                        is DataResult.Success -> {
                            _isLoading.value = false
                            if (page == 1) {
                                _characters.clear()
                            }
                            updateTotalPage(result.data.info.pages)
                            _characters.addAll(result.data.results)
                            _homeState.value = StatusData.Success(
                                HomeUiState(characters = _characters.toList())
                            )
                        }

                        is DataResult.Exception -> {
                            _isLoading.value = false
                            _homeState.value =
                                StatusData.Error(result.throwable.message ?: "Unknown error")
                        }
                    }
                } else {
                    _isLoading.value = false
                }
            } catch (e: Exception) {
                _isLoading.value = false
                _homeState.value = StatusData.Error(e.message ?: "Unknown error")
            }
        }
    }

    private fun updateTotalPage(pages: Int) {
        if (totalPage == 1) totalPage = pages
        totalPage = pages
    }

    private fun validCallApi(pageCurrency: Int): Boolean {

        return (totalPage >= pageCurrency)
    }
}

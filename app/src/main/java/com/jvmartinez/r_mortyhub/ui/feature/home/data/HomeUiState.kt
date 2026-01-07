package com.jvmartinez.r_mortyhub.ui.feature.home.data

import com.jvmartinez.r_mortyhub.core.data.model.Character

data class HomeUiState(
    val isLoading: Boolean = false,
    val characters: List<Character> = emptyList(),
    val error: String? = null
)
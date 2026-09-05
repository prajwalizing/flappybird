package com.prajwalhs.flappybird.presentation.features.menu

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.prajwalhs.flappybird.domain.usecase.GetHighScoreUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MenuViewModel @Inject constructor(
    private val getHighScoreUseCase: GetHighScoreUseCase
) : ViewModel() {

    private val _highScore = MutableStateFlow(0)
    val highScore: StateFlow<Int> = _highScore

    init {
        viewModelScope.launch {
            getHighScoreUseCase().collect { _highScore.value = it }
        }
    }
}
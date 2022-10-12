package com.example.classwork10.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.classwork10.domain.model.ChattingModel
import com.example.classwork10.domain.usecases.GetChattingInfoUseCase
import com.gabo.quiz10.comon.handleResponse.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ChattingViewModel @Inject constructor(private val getChatInfoUseCase: GetChattingInfoUseCase) :
    ViewModel() {
    private val _state = MutableStateFlow(ViewState())
    val state = _state.asStateFlow()

    init {
        getChatInfo()
    }

    fun getChatInfo() {
        viewModelScope.launch {
            resetViewState()
            _state.value = _state.value.copy(loading = true)
            getChatInfoUseCase(Unit).collect {
                when (it) {
                    is Resource.Success -> {
                        _state.value = _state.value.copy(loading = false, data = it.data!!)
                    }
                    is Resource.Error -> {
                        _state.value = _state.value.copy(loading = false, errorMsg = it.errorMsg!!)
                    }
                }
            }
        }
    }

    private fun resetViewState() {
        _state.value = _state.value.copy(loading = false, data = emptyList(), errorMsg = "")
    }

    data class ViewState(
        val loading: Boolean = false,
        val data: List<ChattingModel> = emptyList(),
        val errorMsg: String = ""
    )
}
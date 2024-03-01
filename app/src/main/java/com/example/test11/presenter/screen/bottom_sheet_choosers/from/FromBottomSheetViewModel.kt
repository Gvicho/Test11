package com.example.test11.presenter.screen.bottom_sheet_choosers.from

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.test11.domain.common.ResultWrapper
import com.example.test11.domain.usecase.GetAccountsUseCase
import com.example.test11.presenter.mapper.toUI
import com.example.test11.presenter.state.FromState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FromBottomSheetViewModel @Inject constructor(
    private val getAccountsUseCase: GetAccountsUseCase
) :ViewModel() {

    private val _uiStateFlow = MutableStateFlow(FromState())
    val uiStateFlow : StateFlow<FromState> = _uiStateFlow

    private val _oneTimeEventChannel = Channel<String>()
    val oneTimeEventFlow = _oneTimeEventChannel.receiveAsFlow() //takes as a hot flow (not like stateFlow,but like a sharedFlow)

    fun onEvent(){

    }
    init {
        loadPage()
    }

    private fun loadPage() {
        viewModelScope.launch {

            getAccountsUseCase().collect() { result ->
                when (result) {
                    is ResultWrapper.Error -> {
                        _oneTimeEventChannel.send(result.errorMessage ?: "")
                    }

                    is ResultWrapper.Loading -> {
                        _uiStateFlow.update {
                            it.copy(isLoading = result.loading)
                        }

                    }

                    is ResultWrapper.Success -> {
                        _uiStateFlow.update {
                            val accountsList = result.data!!.map { story ->
                                story.toUI()
                            }
                            it.copy(accountsListIsSuccess = accountsList)
                        }
                    }
                }
            }
        }
    }


}
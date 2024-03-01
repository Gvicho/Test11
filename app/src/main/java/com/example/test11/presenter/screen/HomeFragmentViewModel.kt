package com.example.test11.presenter.screen

import androidx.lifecycle.ViewModel
import com.example.test11.domain.usecase.GetAccountsUseCase
import com.example.test11.presenter.event.HomePageEvents
import com.example.test11.presenter.state.FromState
import com.example.test11.presenter.state.HomePageState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class HomeFragmentViewModel@Inject constructor(
    private val getAccountsUseCase: GetAccountsUseCase
) : ViewModel() {

    private val _uiStateFlow = MutableStateFlow(HomePageState())
    val uiStateFlow : StateFlow<HomePageState> = _uiStateFlow

    private val _oneTimeEventChannel = Channel<String>()
    val oneTimeEventFlow = _oneTimeEventChannel.receiveAsFlow() //takes as a hot flow (not like stateFlow,but like a sharedFlow)

    fun onEvent(event: HomePageEvents){
        when(event){
            is HomePageEvents.SetFromAccountFieldState -> _uiStateFlow.update { it.copy(fromAccountIsSuccess = event.account) }
        }
    }
}
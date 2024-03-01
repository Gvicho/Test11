package com.example.test11.presenter.event

import com.example.test11.presenter.model.AccountUi

sealed class HomePageEvents {
    data class SetFromAccountFieldState(val account: AccountUi):HomePageEvents()
}
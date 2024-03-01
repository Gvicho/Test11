package com.example.test11.presenter.state

import com.example.test11.presenter.model.AccountUi

data class HomePageState(
    val isLoading:Boolean = false,
    val fromAccountIsSuccess:AccountUi? = null,
    val toAccountIsSuccess:AccountUi? = null
) {
}
package com.example.test11.presenter.state

import com.example.test11.presenter.model.AccountUi

data class FromState(
    val isLoading:Boolean = false,
    val accountsListIsSuccess:List<AccountUi>? = null
) {
}
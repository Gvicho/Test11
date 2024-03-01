package com.example.test11.presenter.mapper

import com.example.test11.domain.model.Account
import com.example.test11.presenter.model.AccountUi

fun Account.toUI(): AccountUi {
    return AccountUi(
        id = id,
        accountName = accountName,
        accountNumber = accountNumber,
        valueType = valueType,
        cardType = cardType,
        balance = balance,
        cardLogo = cardLogo
    )
}
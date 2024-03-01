package com.example.test11.data.remote.mapper

import com.example.test11.data.remote.model.AccountDto
import com.example.test11.domain.model.Account

fun AccountDto.toDomainModel(): Account {
    return Account(
        id = id,
        accountName = accountName,
        accountNumber = accountNumber,
        valueType = valueType,
        cardType = cardType,
        balance = balance,
        cardLogo = cardLogo
    )
}
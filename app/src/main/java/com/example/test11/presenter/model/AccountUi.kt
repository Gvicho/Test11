package com.example.test11.presenter.model

data class AccountUi(
    val id: Int,
    val accountName: String,
    val accountNumber: String,
    val valueType: String,
    val cardType: String,
    val balance: Double,
    val cardLogo: String?
) {
}
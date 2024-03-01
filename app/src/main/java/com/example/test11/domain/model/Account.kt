package com.example.test11.domain.model

data class Account(
    val id: Int,
    val accountName: String,
    val accountNumber: String,
    val valueType: String,
    val cardType: String,
    val balance: Double,
    val cardLogo: String?
) {
}
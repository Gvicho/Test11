package com.example.test11.data.remote.service

import com.example.test11.data.remote.model.AccountDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface AccountsService {

    @GET("5c74ec0e-5cc1-445e-b64b-b76b286b215f")
    suspend fun getAccounts(): Response<List<AccountDto>>


    @GET("4253786f-3500-4148-9ebc-1fe7fb9dc8d5")
    suspend fun getAccountByNumber(@Query("account_number") accountNumber: String): Response<AccountDto>

}
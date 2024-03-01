package com.example.test11.domain.repository

import com.example.test11.domain.common.ResultWrapper
import com.example.test11.domain.model.Account
import kotlinx.coroutines.flow.Flow


interface AccountsRepository {
    fun getAvailableAccounts() : Flow<ResultWrapper<List<Account>>>
}
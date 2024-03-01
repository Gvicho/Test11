package com.example.test11.domain.usecase

import com.example.test11.domain.common.ResultWrapper
import com.example.test11.domain.model.Account
import com.example.test11.domain.repository.AccountsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAccountsUseCase@Inject constructor(
    private val accountsRepository: AccountsRepository
) {
    operator fun invoke(): Flow<ResultWrapper<List<Account>>> {
        return accountsRepository.getAvailableAccounts()
    }
}
package com.example.test11.data.repository

import com.example.test11.data.common.HandleResponse
import com.example.test11.data.common.mapResultWrapper
import com.example.test11.data.remote.mapper.toDomainModel
import com.example.test11.data.remote.service.AccountsService
import com.example.test11.domain.common.ResultWrapper
import com.example.test11.domain.model.Account
import com.example.test11.domain.repository.AccountsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AccountsRepositoryImpl @Inject constructor(
    private val accountsService:AccountsService,
    private val handler: HandleResponse
) :AccountsRepository {

    override fun getAvailableAccounts(): Flow<ResultWrapper<List<Account>>> {
        return handler.safeApiCall {
            accountsService.getAccounts()
        }.mapResultWrapper {list->
            list.map {
                it.toDomainModel()
            }
        }
    }
}
package com.example.test11.data.common

import android.util.Log
import com.example.test11.domain.common.ResultWrapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import javax.inject.Inject


class HandleResponse @Inject constructor() {
    fun <T : Any> safeApiCall(call: suspend () -> Response<T>): Flow<ResultWrapper<T>> = flow {
        try {
            Log.d("tag123","try start loading true")
            emit(ResultWrapper.Loading(loading = true))
            val response = call()
            val body = response.body()
            if (response.isSuccessful && body != null) {
                Log.d("tag123","try Succesfull")
                emit(ResultWrapper.Success(data = body))
            } else {
                Log.d("tag123","try error ${response.errorBody()}}")
                emit(ResultWrapper.Error(errorMessage = response.errorBody()?.string() ?: ""))
            }
        } catch (e: Throwable) {
            Log.d("tag123","try fetch error $e")
            emit(ResultWrapper.Error(errorMessage = e.message ?: ""))
        } finally {
            emit(ResultWrapper.Loading(loading = false))
        }
    }
}
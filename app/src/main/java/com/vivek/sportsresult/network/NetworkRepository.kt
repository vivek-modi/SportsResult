package com.vivek.sportsresult.network

import android.util.Log
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Response
import java.io.IOException

private const val DEFAULT_ERROR_CODE = -1
private const val DEFAULT_ERROR_MESSAGE = "Something went wrong"

open class NetworkRepository {

    suspend fun <T : Any> apiCall(call: Call<T>): ApiResponse<T> {

        val apiResponse: ApiResponse<T> = getApiResult(call)

        when (apiResponse) {
            is ApiResponse.Success ->
                Log.d("SUCCESS TAG", "ApiResponse success")
            is ApiResponse.Error -> {
                Log.d("ERROR TAG","Exception - ${apiResponse.exception}")
            }
        }
        return apiResponse
    }

    private suspend fun <T : Any> getApiResult(call: Call<T>): ApiResponse<T> {
        return withContext(Dispatchers.IO) {
            var response: Response<T>? = null
            try {
                response = call.execute()
            } catch (throwable: Throwable) {
                Log.e("getApiResult tag", "API call error", throwable)
            }

            when (response?.isSuccessful) {
                true -> {
                    return@withContext ApiResponse.Success(response.body())
                }
                else -> {
                    var responseMessage = DEFAULT_ERROR_MESSAGE
                    var errorCode = DEFAULT_ERROR_CODE
                    var errorResponse: ErrorResponse? = null
                    response?.let {
                        responseMessage = response.message()
                        errorCode = response.code()
                        response.errorBody()?.let {
                            errorResponse = parseErrorResponse(it.string())
                            errorResponse?.errorMessage?.let { message ->
                                responseMessage = message
                            }
                        }
                    }

                    return@withContext ApiResponse.Error(
                        IOException("Error: $responseMessage ($errorCode)"),
                        errorCode,
                        errorResponse
                    )
                }
            }
        }
    }

    private fun parseErrorResponse(responseJson: String): ErrorResponse? {
        val moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()
        val adapter: JsonAdapter<ErrorResponse> = moshi.adapter(ErrorResponse::class.java)
        var response: ErrorResponse? = null
        try {
            response = adapter.fromJson(responseJson)
        } catch (e: Throwable) {
            Log.e("parseErrorResponse tag", "Failed to parse error response", e)
        }

        return response
    }
}
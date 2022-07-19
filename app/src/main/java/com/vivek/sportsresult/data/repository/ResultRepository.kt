package com.vivek.sportsresult.data.repository

import com.vivek.sportsresult.data.sportresult.SportResultResponse
import com.vivek.sportsresult.network.ApiResponse
import com.vivek.sportsresult.network.NetworkRepository

class ResultRepository(private val resultApi: ResultApi) : NetworkRepository() {

    suspend fun getSportResult(): ApiResponse<SportResultResponse> {
        return apiCall(resultApi.getSportResult())
    }
}
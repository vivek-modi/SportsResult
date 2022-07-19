package com.vivek.sportsresult.data.repository

import com.vivek.sportsresult.data.sportresult.SportResultResponse
import retrofit2.Call
import retrofit2.http.POST

interface ResultApi {

    @POST("results")
    fun getSportResult(): Call<SportResultResponse>
}
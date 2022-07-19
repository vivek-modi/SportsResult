package com.vivek.sportsresult.data

import com.vivek.sportsresult.network.NetworkRepository

class ResultRepository(private val resultApi: ResultApi) : NetworkRepository() {

    suspend fun getSportResult() {}
}
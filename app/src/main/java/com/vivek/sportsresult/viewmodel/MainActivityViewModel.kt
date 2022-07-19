package com.vivek.sportsresult.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vivek.sportsresult.core.logE
import com.vivek.sportsresult.data.repository.ResultRepository
import kotlinx.coroutines.launch

class MainActivityViewModel(private val resultRepository: ResultRepository) : ViewModel() {

    init {
        getSportResult()
    }

    fun getSportResult() {
        viewModelScope.launch {
            val response = resultRepository.getSportResult()
            response.handleResult(
                onSuccess = {
                    logE("On Success >> $it")
                },
                onError = {
                    logE("On Error >> $it")
                }
            )
        }
    }
}
package com.vivek.sportsresult.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vivek.sportsresult.data.repository.ResultRepository
import com.vivek.sportsresult.data.ResultFetchState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class MainActivityViewModel(private val resultRepository: ResultRepository) : ViewModel() {

    val stateResultFetchState = MutableStateFlow<ResultFetchState>(ResultFetchState.OnEmpty)

    fun getSportResult() {
        viewModelScope.launch {
            val result = resultRepository.getSportResult()
            delay(5000)
            result.handleResult(
                onSuccess = { response ->
                    if (response != null) {
                        stateResultFetchState.value = ResultFetchState.IsLoading(false)
                        stateResultFetchState.value = ResultFetchState.OnSuccess(response)
                    } else {
                        stateResultFetchState.value = ResultFetchState.OnEmpty
                    }
                },
                onError = {
                    stateResultFetchState.value =
                        ResultFetchState.OnError(it.errorResponse?.errorMessage)
                }
            )
        }
    }
}
package com.vivek.sportsresult.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MainActivityViewModel : ViewModel() {

    fun getSportResult() {
        viewModelScope.launch {

        }
    }
}
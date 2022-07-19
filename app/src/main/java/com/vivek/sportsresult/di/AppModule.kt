package com.vivek.sportsresult.di

import com.vivek.sportsresult.viewmodel.MainActivityViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val appModule = module {
    viewModelOf(::MainActivityViewModel)
}
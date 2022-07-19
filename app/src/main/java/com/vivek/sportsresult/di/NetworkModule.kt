package com.vivek.sportsresult.di

import com.vivek.sportsresult.data.ResultApi
import com.vivek.sportsresult.data.ResultRepository
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit

const val NETWORK_SESSION_SCOPE_NAME = "networkSession"
const val API_NAME_TAG = "api"

val networkModule = module {

    single {

        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BASIC

        val builder = OkHttpClient().newBuilder()
            .addInterceptor(httpLoggingInterceptor)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(40, TimeUnit.SECONDS)

        builder.build()
    }

    scope(named(NETWORK_SESSION_SCOPE_NAME)) {
        scoped { get<Retrofit>(named(API_NAME_TAG)).create(ResultApi::class.java) }
        scoped { ResultRepository(get()) }
    }
}
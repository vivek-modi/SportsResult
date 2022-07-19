package com.vivek.sportsresult.di

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.vivek.sportsresult.data.repository.ResultApi
import com.vivek.sportsresult.data.repository.ResultRepository
import com.vivek.sportsresult.network.HeadersInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

const val NETWORK_SESSION_SCOPE_NAME = "networkSession"
const val API_NAME_TAG = "api"
const val BASE_URL = "https://ancient-wood-1161.getsandbox.com:443/"

val networkModule = module {

    single {

        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BASIC

        val builder = OkHttpClient().newBuilder()
            .addInterceptor(httpLoggingInterceptor)
            .addInterceptor(HeadersInterceptor())
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(40, TimeUnit.SECONDS)

        builder.build()
    }

    scope(named(NETWORK_SESSION_SCOPE_NAME)) {

        scoped<Retrofit>(qualifier = named(API_NAME_TAG)) {
            val moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()
            Retrofit.Builder()
                .client(get())
                .baseUrl(BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .build()
        }

        scoped { get<Retrofit>(named(API_NAME_TAG)).create(ResultApi::class.java) }
        scoped { ResultRepository(get()) }
    }
}
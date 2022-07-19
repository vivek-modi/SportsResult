package com.vivek.sportsresult.network

import okhttp3.Interceptor
import okhttp3.Response

private const val HEADER_NAME = "Content-Type"
private const val HEADER_VALUE = "application/json"

class HeadersInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request().newBuilder()
        requestBuilder.addHeader(HEADER_NAME, HEADER_VALUE)
        return chain.proceed(requestBuilder.build())
    }
}
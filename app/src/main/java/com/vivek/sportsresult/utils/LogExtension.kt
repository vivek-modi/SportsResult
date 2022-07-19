package com.vivek.sportsresult.utils

import android.util.Log
import com.vivek.sportsresult.BuildConfig

private fun getClassNameForLogTag(anyClass: Any): String {
    val tagLengthLimit = 50
    var name = anyClass::class.java.simpleName
    if (name.isEmpty()) {
        name = "UNKNOWN"
    }

    if (name.length > tagLengthLimit) {
        name = name.substring(0, tagLengthLimit)
    }
    return name
}

fun Any.logD(log: String) {
    if (BuildConfig.DEBUG) {
        Log.d(getClassNameForLogTag(this), log)
    }
}

fun Any.logE(log: String, throwable: Throwable? = null) {
    if (BuildConfig.DEBUG) {
        Log.e(getClassNameForLogTag(this), log, throwable)
    }
}
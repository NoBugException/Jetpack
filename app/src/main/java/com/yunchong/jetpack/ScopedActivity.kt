package com.yunchong.jetpack

import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel

abstract class ScopedActivity: AppCompatActivity(), CoroutineScope by MainScope(){

    protected val coroutineExceptionHandler = CoroutineExceptionHandler { coroutineCotext, exception ->
        println("exception:${exception}")
        val exceptionStacks = exception.stackTrace
        for (index in exceptionStacks.indices) {
            println("exception:" + exceptionStacks[index])
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        cancel()
    }
}
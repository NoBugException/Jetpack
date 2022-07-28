package com.yunchong.jetpack.utils

import android.content.Context
import android.content.Intent

/**
 * 启动 Activity
 */
inline fun <reified T> startActivity(context: Context, block: Intent.() -> Unit) {
    val intent = Intent(context, T::class.java)
    intent.block()
    context.startActivity(intent)
}
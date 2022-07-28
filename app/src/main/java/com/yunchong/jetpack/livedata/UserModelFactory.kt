package com.yunchong.jetpack.livedata

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class UserModelFactory(private val application: Application) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UserModel::class.java)) {
            return UserModel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
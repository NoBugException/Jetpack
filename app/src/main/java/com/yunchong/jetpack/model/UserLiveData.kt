package com.yunchong.jetpack.model

import androidx.annotation.MainThread
import androidx.lifecycle.LiveData
import com.yunchong.jetpack.bean.User

class UserLiveData : LiveData<User>() {
    companion object {

        private lateinit var mInstance: UserLiveData

        @JvmStatic
        @MainThread
        fun get(): UserLiveData {
            mInstance = if (::mInstance.isInitialized) {
                mInstance
            } else {
                UserLiveData()
            }
            return mInstance
        }
    }

    override fun onActive() {
        super.onActive()
    }

    override fun onInactive() {
        super.onInactive()
    }

    /**
     * 更新数据
     */
    fun updateUserInfo() {
        var user = get().value
        if (user == null) {
            user = User("", 0)
        }
        user.apply {
            userName = "张三"
            age = (1..100).random()
        }
        value = user // 或者 将 value = user 改成 postValue(user)
    }
}
package com.yunchong.jetpack.startup

class TestInstance {

    companion object {
        fun getInstance(): TestInstance {
            return INSTANCE.instance
        }
    }

    private object INSTANCE {
        val instance = TestInstance()
    }
}
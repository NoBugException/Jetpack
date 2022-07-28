package com.yunchong.jetpack.fastsp

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.yunchong.fastsharedpreferences.FastSharedPreferences
import com.yunchong.jetpack.R
import com.yunchong.jetpack.databinding.ActivityPreloaderBinding

// Github地址：https://github.com/JeremyLiao/FastSharedPreferences

// FastSharedPreferences 是一个Android平台的高性能key-value组件;
// 特点:
// 1、实现了SharedPreferences和SharedPreferences.Editor接口，便于无缝替换SharedPreferences。
// 2、增强了SharedPreferences，增加了写入/读取Serializable的接口。
// 3、高效的写入/读取性能，读写性能相比SharedPreferences增强了200-300倍
// 4、纯java实现，很好的兼容性和稳定性
// 5、实现代码简洁，aar体积很小
// 6、支持跨进程使用（同时实现高性能和跨进程的难度超出预期，这一点后面会逐渐优化）
// 7、支持基于LRU的缓存管理，减少内存使用

// 原理：
// 1、适合高强度/高频次写入读取
// FastSharedPreferences由Cache层和同步层构成，特别适合高强度/高频次写入读取数据。
// 2、内存Cache
// 基于ConcurrentHashMap的内存Cache层，不仅提高了写入性能，也极大的提高了读取性能。
// 3、同步管理
// 通过脏数据标记等技术，减少了同步的次数，使得慢速的I/O不再成为读写速度的瓶颈。

// FastSharedPreferences使用指南：
// 在application onCreate中初始化:FastSharedPreferences.init(this)
//    写入：
//    val sharedPreferences: FastSharedPreferences = FastSharedPreferences.get("fileName")
//    sharedPreferences.edit().putInt("test_key", 100).apply()
//    读取：
//    val sharedPreferences = FastSharedPreferences.get("fileName")
//    val ret = sharedPreferences.getInt("test_key", -1)
//


class FastSharedPreferencesActivity : AppCompatActivity() {

    private lateinit var binding : ActivityPreloaderBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView<ActivityPreloaderBinding>(this@FastSharedPreferencesActivity,
            R.layout.activity_preloader
        )

        binding.fastspWrite.setOnClickListener { // 写入
            val sharedPreferences: FastSharedPreferences = FastSharedPreferences.get("test")
            sharedPreferences.edit().putInt("test_key", 100).apply()
        }
        binding.fastspRead.setOnClickListener { // 读取
            val sharedPreferences = FastSharedPreferences.get("test")
            val ret = sharedPreferences.getInt("test_key", -1)
            Log.d("XXX", "ret:$ret")
        }
    }
}
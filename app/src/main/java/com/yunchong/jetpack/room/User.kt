package com.yunchong.jetpack.room

import androidx.room.*

/**
 * 用户表
 */
@Entity(tableName = "user")
data class User (
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "user_id") val id: Long = 0,
    @ColumnInfo(name = "user_account") val account: String, // 账号
    @ColumnInfo(name = "user_pwd") val pwd: String, // 密码
    @ColumnInfo(name = "user_name") val name: String,
    @Embedded var address: Address, // 地址
    @ColumnInfo(name = "user_status") val status: Int
    // @ColumnInfo(name = "user_temp") val temp: Int
)
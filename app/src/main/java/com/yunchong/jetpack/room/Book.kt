package com.yunchong.jetpack.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * 书籍表
 */
@Entity(tableName = "book")
data class Book(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "book_id") var id: Long, // 图书id
    @ColumnInfo(name = "book_name") var name: String, // 书名
    @ColumnInfo(name = "book_price") var price: Float // 价格
)
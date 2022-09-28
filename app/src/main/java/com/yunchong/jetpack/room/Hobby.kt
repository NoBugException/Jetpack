package com.yunchong.jetpack.room

import androidx.room.*
import androidx.room.ForeignKey.CASCADE

/**
 * 爱好表
 */
@Entity(tableName = "hobby", foreignKeys = [
    ForeignKey(entity = User::class, parentColumns = ["user_id"], childColumns = ["user_id"], onDelete = CASCADE),
    ForeignKey(entity = Book::class, parentColumns = ["book_id"], childColumns = ["book_id"], onDelete = CASCADE)
], indices = [
    Index(value = ["user_id"], unique = true),
    Index(value = ["book_id"], unique = false)
])
data class Hobby(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "hobby_id") val id: Long = 0, // 爱好ID
    @ColumnInfo(name = "user_id") var user_id: Long, // 用户ID
    @ColumnInfo(name = "book_id") var book_id: Long // 图书ID
)
package com.yunchong.jetpack.room

import androidx.room.*

@Dao
interface BookDao {

    @Query("select * from book where book_id = :id")
    fun getBookById(id: Long): Book

    @Query("select * from book")
    fun getAllBooks(): List<Book>

    // OnConflictStrategy.REPLACE: 如果记录已存在，则直接替换
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addBook(book: Book)

    @Delete
    fun deleteBookByBook(book: Book)

    @Query("delete from book where book_id = :id ")
    fun deleteBookById(id: Long)

    @Update
    fun updateBookByBook(book: Book)

    @Query("update book set book_name = :updateName where book_id = :id")
    fun update(id: Long, updateName: String)
}
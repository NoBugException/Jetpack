package com.yunchong.jetpack.room

import androidx.room.*

@Dao
interface UserDao {

    @Query("select * from user where user_id = :id")
    fun getUserById(id: Long): User

    @Query("select * from user")
    fun getAllUsers(): List<User>

    // OnConflictStrategy.REPLACE: 如果记录已存在，则直接替换
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addUser(user: User)

    @Delete
    fun deleteUserByUser(user: User)

    @Query("delete from user where user_id = :id ")
    fun deleteUserById(id: Long)

    @Update
    fun updateUserByUser(user: User)

    @Query("update user set user_name = :updateName where user_id = :id")
    fun update(id: Long, updateName: String)
}
package com.yunchong.jetpack.room

import androidx.room.*

@Dao
interface HobbyDao {

    @Query("select * from hobby where hobby_id = :id")
    fun getHobbyById(id: Long): Hobby

    @Query("select * from hobby")
    fun getAllHobbys(): List<Hobby>

    // OnConflictStrategy.REPLACE: 如果记录已存在，则直接替换
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addHobby(hobby: Hobby)

    @Delete
    fun deleteHobbyByHobby(hobby: Hobby)

    @Query("delete from hobby where hobby_id = :id ")
    fun deleteHobbyById(id: Long)

    @Update
    fun updateHobbyByHobby(hobby: Hobby)
}
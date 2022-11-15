package com.fcmb.sampletestingappwithcicd.roomdb.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.fcmb.sampletestingappwithcicd.roomdb.entity.Blog

@Dao
interface BlogDao {
    @Insert
    fun insert(blog: Blog)

    @Delete
    fun delete(blog: Blog)

    @Query("SELECT * FROM Blog")
    suspend fun getAllBlogs(): List<Blog>

    @Update
    fun update(blog: Blog)
}

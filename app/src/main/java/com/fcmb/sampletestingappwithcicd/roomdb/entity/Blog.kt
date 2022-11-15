package com.fcmb.sampletestingappwithcicd.roomdb.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Blog")
data class Blog(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    val title: String,
    val body: String,
    val author: String
)

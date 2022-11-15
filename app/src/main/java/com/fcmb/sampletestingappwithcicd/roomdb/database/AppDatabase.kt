package com.fcmb.sampletestingappwithcicd.roomdb.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteOpenHelper
import com.fcmb.sampletestingappwithcicd.roomdb.dao.BlogDao
import com.fcmb.sampletestingappwithcicd.roomdb.entity.Blog
import net.sqlcipher.database.SupportFactory

@Database(entities = [Blog::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun blogDao(): BlogDao

    companion object {
        // Use a user-entered passphrase to encrypt/decrypt
        private val passPhrase: ByteArray = "password".encodeToByteArray()
        private val sqlCipherSupportFactory: SupportSQLiteOpenHelper.Factory =
            SupportFactory(passPhrase)
        private var INSTANCE: AppDatabase? = null
        fun getInstance(context: Context): AppDatabase? {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(
                    context.applicationContext, AppDatabase::class.java,
                    "blogDB"
                ).openHelperFactory(sqlCipherSupportFactory)
                    .allowMainThreadQueries().build()
            }
            return INSTANCE
        }

        fun getBlogDao(): BlogDao? {
            return INSTANCE?.blogDao()
        }
    }
}

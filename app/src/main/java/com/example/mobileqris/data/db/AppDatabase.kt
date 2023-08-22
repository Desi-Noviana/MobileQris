package com.example.mobileqris.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.mobileqris.data.db.dao.UserDao
import com.example.mobileqris.data.models.QrisScanModels

@Database(entities = [QrisScanModels::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}
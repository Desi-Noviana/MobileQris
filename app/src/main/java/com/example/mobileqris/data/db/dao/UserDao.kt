package com.example.mobileqris.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.mobileqris.data.models.QrisScanModels


@Dao
interface UserDao {

    @Query("SELECT * FROM qrisscanmodels")
    suspend fun gettras(): List<QrisScanModels>

    @Insert
    suspend fun insert(dataList: QrisScanModels)

    @Query("DELETE FROM QrisScanModels")
    suspend fun deleteAll()
}
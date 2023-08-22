package com.example.mobileqris.data.repository

import com.example.mobileqris.data.db.dao.UserDao
import com.example.mobileqris.data.models.QrisScanModels
import javax.inject.Inject

class QrisRepository @Inject constructor(
    private val userDao: UserDao
){

    suspend fun insertTras(data: QrisScanModels) {
        userDao.insert(data)
    }
    suspend fun getData(): List<QrisScanModels>{
        return userDao.gettras()
    }
}
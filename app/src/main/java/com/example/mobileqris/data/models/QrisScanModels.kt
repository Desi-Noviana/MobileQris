package com.example.mobileqris.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class QrisScanModels(
    var bankName: String? = null,
    @PrimaryKey var idTransaksi: String = "",
    var namaMerchant: String? = null,
    var nominalTransaksi: String? = null
)
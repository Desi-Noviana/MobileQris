package com.example.mobileqris.data.extensi

import android.content.Context
import com.example.mobileqris.data.models.QrisScanModels
import com.example.mobileqris.data.models.UserModels
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale


fun String.splitToQRScan(): QrisScanModels? {

    var data: QrisScanModels? = null
    if (this.split(".").size == 4){
        val listData = this.split(".")
        data = QrisScanModels(
            bankName = listData[0],
            idTransaksi = listData[1],
            namaMerchant = listData[2],
            nominalTransaksi = listData[3]
        )
    }

    return data
}

fun saveUser(context: Context, data: UserModels){
    val sharedPreferences =
        context.getSharedPreferences(KEY_SAVE.usersavedata, Context.MODE_PRIVATE)
    val editor = sharedPreferences.edit()
    editor.putInt("id", data.id!!)
    editor.putString("name", data.name)
    editor.putInt("saldo", data.saldo!!)
    editor.putString("date", data.date)
    editor.apply()
}

fun getUser(context: Context): UserModels? {
    val sharedPreferences =
        context.getSharedPreferences(KEY_SAVE.usersavedata, Context.MODE_PRIVATE)
    val id = sharedPreferences.getInt("id", 0)
    val name = sharedPreferences.getString("name", null)
    val saldo = sharedPreferences.getInt("saldo", 0)
    val date = sharedPreferences.getString("date", null)
    return UserModels(id, name, saldo, date)
}

fun updateUser(context: Context, user: UserModels) {

    saveUser(context, user)
}

fun getTimeDevide():String{

    // Get date & time today
    val calendar = Calendar.getInstance()

    val dayOfWeek = calendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.ENGLISH)

    val dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH)
    val month = calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.ENGLISH)
    val year = calendar.get(Calendar.YEAR)

    val formattedDate = SimpleDateFormat("dd MMMM yyyy", Locale.ENGLISH).format(calendar.time)
    println("Tanggal Lengkap: $formattedDate")

    return "$dayOfWeek, $dayOfMonth-$month-$year"
}

object KEY_SAVE {

    val usersavedata = "MySharedPreferences"
}


package com.example.mobileqris

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mobileqris.data.extensi.getTimeDevide
import com.example.mobileqris.data.extensi.getUser
import com.example.mobileqris.data.extensi.saveUser
import com.example.mobileqris.data.extensi.updateUser
import com.example.mobileqris.data.models.QrisScanModels
import com.example.mobileqris.data.models.UserModels
import com.example.mobileqris.data.viewModels.QrisViewModel
import com.example.mobileqris.ui.theme.MobileQrisTheme
import com.example.mobileqris.ui.view.HomeScreen
import com.example.mobileqris.ui.view.MyScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val viewModel: QrisViewModel = viewModel()

            MyScreen()
            var userData by remember { mutableStateOf(getUser(this@MainActivity)) }

            val dataList = remember { mutableStateListOf<QrisScanModels>() }
            if (userData?.name == null) {
                saveUser(
                    this@MainActivity,
                    UserModels(
                        1, "Desi Noviana",
                        500000,
                        getTimeDevide()
                    )
                )
                userData = getUser(this@MainActivity)
            }

            viewModel.getData().observe(this){
                it.data?.let {
                    dataList.addAll(it)
                    Log.e("Desi set data", it.size.toString())
                }
            }


            HomeScreen(
                accountInfo = userData!!, dataList,
                onClick = {
                    startActivity(Intent(this, ScanQrisActivity::class.java))
                }) {
                val userupdate = UserModels(
                    userData?.id,
                    userData?.name,
                    500000 + userData?.saldo!!,
                    userData?.date
                )
                updateUser(this@MainActivity, userupdate)
                userData = getUser(this@MainActivity)
            }
        }
    }
}
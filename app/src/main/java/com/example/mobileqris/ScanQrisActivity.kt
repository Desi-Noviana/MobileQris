package com.example.mobileqris

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.core.app.ComponentActivity
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mobileqris.data.extensi.getUser
import com.example.mobileqris.data.extensi.splitToQRScan
import com.example.mobileqris.data.extensi.updateUser
import com.example.mobileqris.data.models.QrisScanModels
import com.example.mobileqris.data.models.UserModels
import com.example.mobileqris.data.viewModels.QrisViewModel
import com.example.mobileqris.ui.theme.MobileQrisTheme
import com.example.mobileqris.ui.view.AddButton
import com.example.mobileqris.ui.view.CameraPreview
import com.example.mobileqris.ui.view.CardQRView
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ScanQrisActivity : androidx.activity.ComponentActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            var userData by remember { mutableStateOf(getUser(this@ScanQrisActivity)) }
            val viewModel: QrisViewModel = viewModel()
            MobileQrisTheme {

                MainScreen { result ->
                    if (userData?.saldo!! >= result.nominalTransaksi!!.toInt()) {
                        val userupdate = UserModels(
                            userData?.id,
                            userData?.name,
                            userData?.saldo!! - result.nominalTransaksi!!.toInt(),
                            userData?.date
                        )
                        updateUser(this@ScanQrisActivity, userupdate)
                        userData = getUser(this@ScanQrisActivity)
                        viewModel.saveData(result).observe(this) {
                            Toast.makeText(this, "Transaksi berhasil", Toast.LENGTH_SHORT).show()
                            startActivity(Intent(this, MainActivity::class.java))
                            finish()
                        }

                    } else {
                        Toast.makeText(this, "saldo tidak cukup", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this, MainActivity::class.java))
                        finish()
                    }

                }
            }
        }
    }
}

@Composable
fun MainScreen(
    onClick: (QrisScanModels) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        var scantext by remember { mutableStateOf("") }
        CameraPreview(
            modifier = Modifier
        ) {
            scantext = it
            Log.e("DesiScan", it)
        }

        if (scantext.splitToQRScan() != null) {
            CardQRView(
                modifier = Modifier.padding(end = 16.dp, start = 16.dp), scantext.splitToQRScan()
            )
            AddButton {
                scantext.splitToQRScan()?.let {
                    onClick(it)
                }

            }
        }
    }
}
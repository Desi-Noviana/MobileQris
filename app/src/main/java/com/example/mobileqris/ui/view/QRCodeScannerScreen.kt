package com.example.mobileqris.ui.view

import android.Manifest
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun MyScreen() {
    val context = LocalContext.current
    var hasCameraPermission by remember { mutableStateOf(false) }

    // Fungsi untuk menghandle hasil permintaan izin kamera
    val onCameraPermissionResult: (Boolean) -> Unit = { isGranted ->
        hasCameraPermission = isGranted
    }

    // Memanggil fungsi PermissionRequestDialog untuk meminta izin kamera
    PermissionRequestDialog(
        permission = Manifest.permission.CAMERA, // Menggunakan konstanta dari Android Manifest
        onResult = onCameraPermissionResult
    )

    // Tampilkan UI berdasarkan izin kamera yang diizinkan atau tidak
    if (hasCameraPermission) {
        // Tampilkan komponen yang memerlukan izin kamera di sini
        Text(text = "Izin kamera diberikan!")
    } else {
        Text(text = "Tidak diizinkan untuk mengakses kamera.")
    }
}

@Preview
@Composable
fun PreviewMyScreen() {
    MyScreen()
}
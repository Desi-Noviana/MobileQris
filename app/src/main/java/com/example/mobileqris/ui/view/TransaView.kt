package com.example.mobileqris.ui.view

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mobileqris.data.models.QrisScanModels

@Composable
fun CardQRView(modifier: Modifier, data: QrisScanModels?) {

    Column(modifier = modifier) {
        Surface(
            // Adjust the size of the box as needed
            shape = RoundedCornerShape(8.dp),
            border = BorderStroke(1.dp, Color.Gray),
        ) {
            Column(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
            ) {
                Row( modifier = Modifier.fillMaxWidth()) {
                    Text(
                        modifier = Modifier.weight(1f),
                        text = "${data?.idTransaksi}",
                        style = TextStyle(
                            fontSize = 18.sp,
                            color = Color.Gray,
                            fontWeight = FontWeight.Bold,
                            fontFamily = FontFamily.Monospace
                        )
                    )
                    Text(
                        text = "${data?.bankName}",
                        style = TextStyle(
                            fontSize = 18.sp,
                            color = Color.Gray,
                            fontWeight = FontWeight.Bold,
                            fontFamily = FontFamily.Monospace
                        )
                    )
                }

                Text(
                    modifier = Modifier
                        .padding(top = 8.dp),
                    text = "Nama marchant: ${data?.namaMerchant}",
                    style = TextStyle(
                        fontSize = 14.sp,
                        color = Color.Gray,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.Monospace
                    )
                )
                Text(
                    modifier = Modifier
                        .align(Alignment.End)
                        .padding(top = 8.dp),
                    text = "Biaya : Rp ${data?.nominalTransaksi}",
                    style = TextStyle(
                        fontSize = 14.sp,
                        color = Color.Gray,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.Monospace
                    )
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun showCard() {
    CardQRView(modifier = Modifier, QrisScanModels("BNI", "123123123", "Batam", "50000"))
}
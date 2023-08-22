package com.example.mobileqris.ui.view

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.mobileqris.R
import com.example.mobileqris.data.models.QrisScanModels
import com.example.mobileqris.data.models.UserModels

@Composable
fun HomeScreen(
    accountInfo: UserModels,
    listTran: SnapshotStateList<QrisScanModels>,
    onClick: () -> Unit,
    addSaldo: () -> Unit
) {

    Column(modifier = Modifier.fillMaxSize()) {
        Surface(
            shape = RoundedCornerShape(bottomEnd = 16.dp, bottomStart = 16.dp),
            border = BorderStroke(2.dp, Color.Red),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        modifier = Modifier.weight(1f),
                        text = "Hello, ${accountInfo.name}!",
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Text(
                        text = "Saldo: Rp ${accountInfo.saldo}",
                        style = MaterialTheme.typography.bodySmall
                    )
                    IconButton(
                        onClick = {
                            addSaldo()
                        },
                        modifier = Modifier
                            .size(25.dp)
                    ) {
                        Icon(
                            modifier = Modifier.fillMaxSize(),
                            painter = painterResource(id = R.drawable.baseline_add_box_24), // Ganti dengan sumber daya ikon Anda
                            contentDescription = "Add Saldo"
                        )
                    }
                }

                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Tanggal pembuatan akun: ${accountInfo.date}",
                    style = MaterialTheme.typography.bodySmall
                )
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
        Column(modifier = Modifier.weight(1f)) {

            ItemList(listTran)

        }

        AddButton {
            onClick()
        }

    }


}

@Composable
fun AddButton(onClick: () -> Unit) {
    Surface(
        modifier = Modifier
            .clickable {
                onClick()
            }
            .fillMaxWidth()
            .padding(16.dp),
        color = MaterialTheme.colorScheme.surface
    ) {
        Button(
            onClick = {
                onClick()
            },
        ) {
            Text(text = "Add", style = MaterialTheme.typography.bodyLarge)
        }
    }
}


@Composable
fun ItemList(listTran: SnapshotStateList<QrisScanModels>) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        content = {
            items(listTran) { item ->
                ListItem(item)
            }
        }
    )
}

@Composable
fun ListItem(item: QrisScanModels) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.surface
    ) {
        CardQRView(modifier = Modifier.padding(top = 8.dp, end = 16.dp, start = 16.dp), data = item)
    }
}
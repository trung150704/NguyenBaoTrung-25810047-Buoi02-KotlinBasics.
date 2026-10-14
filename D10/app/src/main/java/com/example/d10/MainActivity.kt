package com.example.d10

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Lấy DAO instance từ AppDatabase
        val dao = AppDatabase.getDatabase(applicationContext).itemDao()

        setContent {
            MaterialTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    // Truyền Factory để khởi tạo ViewModel chứa tham số DAO
                    val itemViewModel: ItemViewModel = viewModel(
                        factory = object : ViewModelProvider.Factory {
                            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                                @Suppress("UNCHECKED_CAST")
                                return ItemViewModel(dao,) as T
                            }
                        }
                    )
                    ItemAppScreen(viewModel = itemViewModel)
                }
            }
        }
    }
}

@Composable
fun ItemAppScreen(viewModel: ItemViewModel) {
    // Quan sát dữ liệu Room CSDL thông qua StateFlow bằng collectAsState
    val itemList by viewModel.itemListState.collectAsState()

    var nameInput by remember { mutableStateOf("") }
    var priceInput by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text("Quản lý Vật Phẩm (Room Database)", style = MaterialTheme.typography.titleLarge)

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = nameInput,
            onValueChange = { nameInput = it },
            label = { Text("Tên vật phẩm") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = priceInput,
            onValueChange = { priceInput = it },
            label = { Text("Giá tiền ($)") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        Button(
            onClick = {
                viewModel.addItem(nameInput, priceInput)
                nameInput = ""
                priceInput = ""
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Thêm vào CSDL Room")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Danh sách vật phẩm tự động cập nhật nhờ Flow từ Room
        LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            items(itemList, key = { it.id }) { item ->
                Card(modifier = Modifier.fillMaxWidth()) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            Text(text = item.name, style = MaterialTheme.typography.titleMedium)
                            Text(text = "$${item.price}", style = MaterialTheme.typography.bodyMedium)
                        }
                        IconButton(onClick = { viewModel.deleteItem(item) }) {
                            Icon(Icons.Default.Delete, contentDescription = "Xóa")
                        }
                    }
                }
            }
        }
    }
}
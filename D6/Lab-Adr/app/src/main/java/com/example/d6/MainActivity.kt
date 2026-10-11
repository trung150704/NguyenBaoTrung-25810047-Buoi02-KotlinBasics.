package com.example.d6

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.d6.model.Contact
import com.example.d6.ui.theme.D6Theme
import com.example.d6.ui.theme.screens.ContactsScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            D6Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    // 1. Tạo danh sách dữ liệu mẫu cho Contacts
                    val sampleContacts = listOf(
                        Contact("1", "Lê Khải Dân", "0123456789", true),
                        Contact("2", "Pham Minh Trí", "0987654321", true),
                        Contact("3", "Lê Thành Tài", "0555666777", false),
                        Contact("4", "Vũ Quang Trung", "0911223344", false),
                        Contact("5", "Tô Lê Thành", "0933445566", true),
                        Contact("6", "Võ Ngọc Ân", "0944556677", false),
                        Contact("7", "Ngô Bảo Phúc", "0955667788", true),
                        Contact("8", "Nguyễn Thanh Hải", "0966778899", false)
                    )

                    // 2. Gọi ContactsScreen và truyền innerPadding từ Scaffold vào
                    ContactsScreen(
                        contacts = sampleContacts,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}
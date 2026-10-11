package com.example.d7

data class Contact(
    val id: Int,
    val name: String,
    val phone: String
)

val sampleContacts = listOf(
    Contact(1, "Le Khai Dan", "0901234567"),
    Contact(2, "Pham Minh Tri", "0912345678"),
    Contact(3, "Bui Minh Quan", "0987654321")
)
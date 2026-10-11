package com.example.d6.model

data class Contact(
    val id: String,
    val name: String,
    val phoneNumber: String,
    val isFavorite: Boolean = false
)

sealed interface ContactListItem {
    data class Header(val letter: Char) : ContactListItem
    data class ContactItem(val contact: Contact) : ContactListItem
}
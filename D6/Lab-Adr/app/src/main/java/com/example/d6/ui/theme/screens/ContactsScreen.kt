package com.example.d6.ui.theme.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.d6.model.Contact
import com.example.d6.model.ContactListItem
import com.example.d6.ui.theme.components.ContactCard
import com.example.d6.ui.theme.components.FavoriteItem

// Hàm hỗ trợ nhóm danh bạ theo chữ cái đầu tiên
fun buildContactListItems(contacts: List<Contact>): List<ContactListItem> {
    val sortedContacts = contacts.sortedBy { it.name }
    val grouped = sortedContacts.groupBy { it.name.first().uppercaseChar() }
    val list = mutableListOf<ContactListItem>()

    grouped.toSortedMap().forEach { (letter, contactList) ->
        list.add(ContactListItem.Header(letter))
        contactList.forEach { contact ->
            list.add(ContactListItem.ContactItem(contact))
        }
    }
    return list
}

@Composable
fun ContactsScreen(contacts: List<Contact>, modifier: Modifier = Modifier) {
    val favorites = remember(contacts) { contacts.filter { it.isFavorite } }
    val uiItems = remember(contacts) { buildContactListItems(contacts) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        // 1. LazyRow hiển thị danh sách yêu thích
        if (favorites.isNotEmpty()) {
            Text(
                text = "Favorites",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                color = MaterialTheme.colorScheme.primary
            )

            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                contentPadding = PaddingValues(horizontal = 12.dp)
            ) {
                items(
                    items = favorites,
                    key = { "fav_${it.id}" }
                ) { contact ->
                    FavoriteItem(contact = contact)
                }
            }

            HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))
        }

        // 2. LazyColumn kết hợp Sealed Interface và Stable Keys
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(bottom = 16.dp)
        ) {
            items(
                items = uiItems,
                key = { item ->
                    when (item) {
                        is ContactListItem.Header -> "header_${item.letter}"
                        is ContactListItem.ContactItem -> "contact_${item.contact.id}"
                    }
                }
            ) { item ->
                when (item) {
                    is ContactListItem.Header -> {
                        Text(
                            text = item.letter.toString(),
                            style = MaterialTheme.typography.titleSmall,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                        )
                    }
                    is ContactListItem.ContactItem -> {
                        ContactCard(contact = item.contact)
                    }
                }
            }
        }
    }
}
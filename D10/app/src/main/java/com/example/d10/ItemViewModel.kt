package com.example.d10

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ItemViewModel(private val itemDao: ItemDao) : ViewModel() {

    // Chuyển đổi Flow từ Room thành StateFlow để UI quan sát an toàn
    val itemListState: StateFlow<List<Item>> = itemDao.getAllItems()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    // Thêm dữ liệu bất đồng bộ với viewModelScope.launch
    fun addItem(name: String, priceText: String) {
        val price = priceText.toDoubleOrNull() ?: 0.0
        if (name.isNotBlank()) {
            viewModelScope.launch {
                itemDao.insert(Item(name = name, price = price))
            }
        }
    }

    // Xóa dữ liệu bất đồng bộ
    fun deleteItem(item: Item) {
        viewModelScope.launch {
            itemDao.delete(item)
        }
    }
}
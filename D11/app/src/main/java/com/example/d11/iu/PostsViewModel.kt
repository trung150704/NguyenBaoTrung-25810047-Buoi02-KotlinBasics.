package com.example.d11.iu

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.d11.network.PostApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.io.IOException
import retrofit2.HttpException

class PostsViewModel : ViewModel() {
    private val _uiState = MutableStateFlow<PostsUiState>(PostsUiState.Loading)
    val uiState: StateFlow<PostsUiState> = _uiState.asStateFlow()

    init {
        loadPosts()
    }

    fun loadPosts() {
        viewModelScope.launch {
            _uiState.value = PostsUiState.Loading
            _uiState.value = try {
                PostsUiState.Success(PostApi.service.listAll())
            } catch (e: IOException) {
                PostsUiState.Error
            } catch (e: HttpException) {
                PostsUiState.Error
            }
        }
    }
}
package com.example.d11.iu

import com.example.d11.data.Post

sealed interface PostsUiState {
    data object Loading : PostsUiState
    data class Success(val posts: List<Post>) : PostsUiState
    data object Error : PostsUiState
}
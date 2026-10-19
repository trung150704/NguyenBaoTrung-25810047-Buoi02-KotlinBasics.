package com.example.d11.iu

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.example.d11.R
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun PostsScreen(
    modifier: Modifier = Modifier,
    postsViewModel: PostsViewModel = viewModel()
) {
    val uiState by postsViewModel.uiState.collectAsState()

    Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        when (val state = uiState) {
            is PostsUiState.Loading -> {
                CircularProgressIndicator()
            }
            is PostsUiState.Error -> {
                Button(onClick = { postsViewModel.loadPosts() }) {
                    Text("Retry")
                }
            }
            is PostsUiState.Success -> {
                LazyVerticalGrid(
                    columns = GridCells.Adaptive(150.dp),
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(state.posts) { post ->
                        AsyncImage(
                            model = ImageRequest.Builder(LocalContext.current)
                                .data(post.thumbnail)
                                .crossfade(true)
                                .build(),
                            contentDescription = post.title,
                            // Sử dụng icon có sẵn của hệ thống Android
                            placeholder = painterResource(android.R.drawable.ic_menu_gallery),
                            error = painterResource(android.R.drawable.ic_dialog_alert),
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .fillMaxWidth()
                                .aspectRatio(1f)
                        )
                    }
                }
            }
        }
    }
}
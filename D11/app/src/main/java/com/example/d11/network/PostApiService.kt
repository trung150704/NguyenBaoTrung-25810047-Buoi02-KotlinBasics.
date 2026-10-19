package com.example.d11.network

import com.example.d11.data.Post
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.converter.kotlinx.serialization.asConverterFactory

private const val BASE_URL = "https://example.com/"

// Interface định nghĩa các Endpoint API
interface PostApiService {
    @GET("posts")
    suspend fun listAll(): List<Post>

    @GET("posts/{userId}")
    suspend fun listByUser(@Path("userId") userId: String): List<Post>

    @GET("posts/search")
    suspend fun search(@Query("filter") search: String): List<Post>
}

// Retrofit Singleton Instance
private val json = Json { ignoreUnknownKeys = true }

private val retrofit = Retrofit.Builder()
    .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
    .baseUrl(BASE_URL)
    .build()

object PostApi {
    val service: PostApiService by lazy {
        retrofit.create(PostApiService::class.java)
    }
}
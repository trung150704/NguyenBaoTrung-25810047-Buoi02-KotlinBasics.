package com.example.d12.data

import android.content.Context

interface AppContainer {
    val workRepository: WorkRepository
}

class DefaultAppContainer(private val context: Context) : AppContainer {
    override val workRepository: WorkRepository by lazy {
        WorkManagerRepository(context)
    }
}

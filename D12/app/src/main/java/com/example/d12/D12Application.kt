package com.example.d12

import android.app.Application
import com.example.d12.data.AppContainer
import com.example.d12.data.DefaultAppContainer

class D12Application : Application() {
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer(this)
    }
}

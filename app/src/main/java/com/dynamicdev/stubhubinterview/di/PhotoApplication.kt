package com.dynamicdev.stubhubinterview.di

import android.app.Application
import org.koin.core.context.startKoin

class PhotoApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(KoinModule.photoModule)
        }
    }
}
package com.dynamicdev.stubhubinterview.di

import com.dynamicdev.stubhubinterview.model.PhotoApi
import com.dynamicdev.stubhubinterview.repository.PhotoRepository
import com.dynamicdev.stubhubinterview.viewmodel.PhotoViewModel
import org.koin.dsl.module

object KoinModule {
    val photoModule = module {
        single { PhotoApi.initRetrofit().create(PhotoApi::class.java) }
        single { PhotoRepository(get()) }
        single { PhotoViewModel.PhotoViewModelProvider(get())}
    }
}
// photApi
//repositry
//viewmodelprojvider

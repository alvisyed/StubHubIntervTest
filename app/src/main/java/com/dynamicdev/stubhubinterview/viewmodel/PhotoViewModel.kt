package com.dynamicdev.stubhubinterview.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import com.dynamicdev.stubhubinterview.repository.PhotoRepository
import com.dynamicdev.stubhubinterview.repository.ViewState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.withContext

private const val TAG = "PhotoViewModel"
class PhotoViewModel(private val repository: PhotoRepository): ViewModel() {

    /**
     * VM provider to inject repository
     */
    class PhotoViewModelProvider(private val repository: PhotoRepository): ViewModelProvider.Factory{
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return PhotoViewModel(repository) as T
        }
    }

    private val scope = CoroutineScope(Job() + Dispatchers.IO)
    // fetch from repo
    // update View
    val photoLiveData: LiveData<ViewState> = liveData(Dispatchers.IO) {
        val viewState = repository.getPhotos()
        //From documentation:
        //Note that this function suspends until the value is set on the [LiveData].
        //withContext(Dispatchers.Main){
         emit(viewState)
        //}
    }
}
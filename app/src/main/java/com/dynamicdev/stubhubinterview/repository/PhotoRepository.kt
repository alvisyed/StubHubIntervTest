package com.dynamicdev.stubhubinterview.repository

import android.util.Log
import com.dynamicdev.stubhubinterview.model.Hit
import com.dynamicdev.stubhubinterview.model.PhotoApi
import com.dynamicdev.stubhubinterview.model.PresentationData

private const val TAG = "PhotoRepository"
class PhotoRepository(private val photoApi: PhotoApi) {
    // Fetch Data
    // expose to Client (VM)

    /**
     * @param photoName In the future we can fetch by Name
     * @return data type for View and handle the state.
     */
    suspend fun getPhotos(photoName: String = ""): ViewState {
        val response = photoApi.getImageApi()
        return if (response.body() != null && response.isSuccessful){
            response.body()?.hits?.let {
                viewResponseFactory(it)
            } ?: ViewState.ViewError("Unknown error")
        } else{
            viewErrorFactory(response.message())
        }
    }

    private fun viewResponseFactory(response: List<Hit>): ViewState {
        return ViewState.ViewData(
            response.map { hit ->
                PresentationData(
                    hit.largeImageURL,
                    hit.likes,
                    hit.user
                )
            }
        )
    }
    private fun viewErrorFactory(error: String): ViewState{
        return ViewState.ViewError(error)
    }
}
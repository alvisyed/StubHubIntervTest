package com.dynamicdev.stubhubinterview.repository

import com.dynamicdev.stubhubinterview.model.ApiResponse
import com.dynamicdev.stubhubinterview.model.PresentationData

/**
 * ViewState will define the State of the Views
 * @sample [ViewData, ViewError, ViewLoading]
 * doing the assumption Retrofit returns Throwable and String for errors
 */
sealed class ViewState{
    object ViewLoading: ViewState()
    data class ViewData(val viewData: List<PresentationData>): ViewState()
    data class ViewError(val error: String): ViewState()
}

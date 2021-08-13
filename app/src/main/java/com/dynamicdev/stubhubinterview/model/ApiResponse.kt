package com.dynamicdev.stubhubinterview.model

import com.squareup.moshi.Json

data class ApiResponse(
    @Json(name="hits")
    val hits: List<Hit>,
    val total: Int,
    val totalHits: Int
)
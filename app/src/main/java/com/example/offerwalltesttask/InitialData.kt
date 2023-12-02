package com.example.offerwalltesttask

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class InitialData(@Json(name = "data") val ids: List<IdObj>)

package com.example.offerwalltesttask

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ContentImage(override val id: Int, val url: String, override val type: String = "image") : Content
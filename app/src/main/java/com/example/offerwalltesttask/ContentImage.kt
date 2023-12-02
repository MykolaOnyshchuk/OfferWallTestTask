package com.example.offerwalltesttask

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ContentImage(override val id: Int, override val type: String = "image", val url: String) : Content
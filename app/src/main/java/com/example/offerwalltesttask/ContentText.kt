package com.example.offerwalltesttask

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ContentText(override val id: Int, val message: String, override val type: String = "text") : Content

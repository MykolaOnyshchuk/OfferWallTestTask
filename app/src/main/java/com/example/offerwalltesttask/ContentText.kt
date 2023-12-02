package com.example.offerwalltesttask

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ContentText(override val id: Int, override val type: String = "text", val message: String) : Content

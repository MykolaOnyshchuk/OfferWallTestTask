package com.example.offerwalltesttask

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ContentGame(override val id: Int, override val type: String = "game") : Content

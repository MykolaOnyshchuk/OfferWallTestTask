package com.example.offerwalltesttask

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ContentWebView(override val id: Int, val url: String, override val type: String = "webview") : Content

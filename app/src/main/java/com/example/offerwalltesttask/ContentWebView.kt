package com.example.offerwalltesttask

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ContentWebView(override val id: Int, override val type: String = "webview", val url: String) : Content

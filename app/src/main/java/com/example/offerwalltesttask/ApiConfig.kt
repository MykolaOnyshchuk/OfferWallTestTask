package com.example.offerwalltesttask

import com.squareup.moshi.Moshi
import com.squareup.moshi.adapters.PolymorphicJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object ApiConfig {
    val moshi = Moshi.Builder()
        .build()

    val retrofitBuilder = Retrofit.Builder()
        .baseUrl("https://demo3005513.mockable.io/api/v1/")
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()

    val service = retrofitBuilder.create<InitialApi>(InitialApi::class.java)

    val contentMoshiFactory = PolymorphicJsonAdapterFactory.of(Content::class.java, "type")
        .withSubtype(ContentText::class.java, "text")
        .withSubtype(ContentWebView::class.java, "webview")
        .withSubtype(ContentImage::class.java, "image")
        .withSubtype(ContentGame::class.java, "game")

    val polymorphicMoshi = Moshi.Builder()
        .add(contentMoshiFactory)
        .build()

    val polymorphicRetrofitBuilder = Retrofit.Builder()
        .baseUrl("https://demo3005513.mockable.io/api/v1/")
        .addConverterFactory(MoshiConverterFactory.create(polymorphicMoshi))
        .build()

    val contentService = polymorphicRetrofitBuilder.create<ContentApi>(ContentApi::class.java)
}
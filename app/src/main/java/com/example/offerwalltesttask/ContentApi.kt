package com.example.offerwalltesttask

import kotlinx.coroutines.Deferred
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ContentApi {
    @GET("object/{id}")
    suspend fun getContent(@Path("id") id: Int): Content
}
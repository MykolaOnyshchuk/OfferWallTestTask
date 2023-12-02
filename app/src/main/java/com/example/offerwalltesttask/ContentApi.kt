package com.example.offerwalltesttask

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ContentApi {
    @GET("object/{id}")
    fun getContent(@Path("id") id: Int): Call<Content>
}
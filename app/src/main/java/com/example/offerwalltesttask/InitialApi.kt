package com.example.offerwalltesttask
import retrofit2.Call
import retrofit2.http.GET

interface InitialApi {
    @GET("entities/getAllIds")
    fun getInitialObject(): Call<InitialData>
}

package com.example.offerwalltesttask
import kotlinx.coroutines.Deferred
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface InitialApi {
    @GET("entities/getAllIds")
    suspend fun getInitialObject(): InitialData
}

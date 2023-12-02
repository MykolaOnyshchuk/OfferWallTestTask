package com.example.offerwalltesttask

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.squareup.moshi.Moshi
import com.squareup.moshi.adapters.PolymorphicJsonAdapterFactory
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val moshi = Moshi.Builder()
            .build()

        val retrofitBuilder = Retrofit.Builder()
            .baseUrl("https://demo3005513.mockable.io/api/v1/")
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()

        val service = retrofitBuilder.create<InitialApi>(InitialApi::class.java)
        val call = service.getInitialObject()


        call.enqueue(object : Callback<InitialData> {
            override fun onResponse(call: Call<InitialData>, response: Response<InitialData>) {
                if (response.isSuccessful) {
                    val result = response.body()
                    Log.d("DATA", result.toString())
                } else {
                    Log.e("ERR", "Fetch has been failed")
                }
            }

            override fun onFailure(call: Call<InitialData>, t: Throwable) {
                Log.e("EXC", t.toString())
            }
        })

        /*val contentMoshiFactory = PolymorphicJsonAdapterFactory.of(Content::class.java, "type")
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
        val contentCall = contentService.getContent(3)

        contentCall.enqueue(object : Callback<Content> {
            override fun onResponse(call: Call<Content>, response: Response<Content>) {
                if (response.isSuccessful) {
                    val contentResult = response.body()
                    Log.d("DATA", contentResult.toString())
                } else {
                    Log.e("ERR", "Fetch has been failed")
                }
            }

            override fun onFailure(call: Call<Content>, t: Throwable) {
                Log.e("EXC", t.toString())
            }
        })*/

    }
}
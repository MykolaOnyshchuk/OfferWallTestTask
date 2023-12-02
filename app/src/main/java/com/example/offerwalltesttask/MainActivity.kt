package com.example.offerwalltesttask

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Observer
import com.example.offerwalltesttask.ApiConfig
import com.squareup.moshi.Moshi
import com.squareup.moshi.adapters.PolymorphicJsonAdapterFactory
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class MainActivity : AppCompatActivity() {

    private val viewModel = MainViewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        var type: String
        var counter: Int = 1
        var initialData: InitialData
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel.initialData.observe(this, Observer { data ->
            Log.d("DATA", "Initial Data: $data")
            initialData = data
        })

        viewModel.content.observe(this, Observer { content ->
            Log.d("DATA", "Content: $content")
            type = content.type

            when(type) {
                "text" -> supportFragmentManager.beginTransaction().add(R.id.frameLayout, TextFragment()).commit()
                "webview" -> supportFragmentManager.beginTransaction().add(R.id.frameLayout, WebViewFragment()).commit()
                "image" -> supportFragmentManager.beginTransaction().add(R.id.frameLayout, ImageFragment()).commit()
            }
        })

        viewModel.fetchInitialData()


    }
}
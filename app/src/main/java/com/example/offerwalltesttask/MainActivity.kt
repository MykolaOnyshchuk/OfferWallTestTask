package com.example.offerwalltesttask

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

class MainActivity : AppCompatActivity() {

    private val viewModel = MainViewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        val nextBtn: Button
        var type: String
        var counter: Int = 1
        viewModel.contentCount.postValue(counter)
        var initialData: InitialData

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        nextBtn = findViewById(R.id.nextBtn)

        viewModel.initialData.observe(this) { data ->
            Log.d("OBSERVED", "Initial Data: $data")
            initialData = data
        }

        viewModel.content.observe(this) { content ->
            Log.d("OBSERVED", "Content: $content")
            type = content.type

            when (type) {
                "text" -> supportFragmentManager.beginTransaction()
                    .add(R.id.frameLayout, TextFragment()).commit()

                "webview" -> supportFragmentManager.beginTransaction()
                    .add(R.id.frameLayout, WebViewFragment()).commit()

                "image" -> supportFragmentManager.beginTransaction()
                    .add(R.id.frameLayout, ImageFragment()).commit()
            }
        }

        viewModel.fetchInitialData()

        nextBtn.setOnClickListener {
            counter++
            viewModel.contentCount.value = counter
            GlobalScope.async {
                viewModel.fetchContent(counter % 4)
            }
        }
    }
}
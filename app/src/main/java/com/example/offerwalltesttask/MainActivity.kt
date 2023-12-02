package com.example.offerwalltesttask

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.activity.viewModels
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()
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
                    .replace(R.id.frameLayout, TextFragment()).commit()

                "webview" -> supportFragmentManager.beginTransaction()
                    .replace(R.id.frameLayout, WebViewFragment()).commit()

                "image" -> supportFragmentManager.beginTransaction()
                    .replace(R.id.frameLayout, ImageFragment()).commit()

                "game" -> Toast.makeText(this@MainActivity,
                    "Content's type is \"game\", ignore", Toast.LENGTH_SHORT).show()

                else -> Toast.makeText(this@MainActivity,
                    "Type is unknown", Toast.LENGTH_SHORT).show()
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
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
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val nextBtn: Button

        nextBtn = findViewById(R.id.nextBtn)

        viewModel.content.observe(this) { content ->
            Log.d("OBSERVED", "Content: $content")

            when (content.type) {
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

        if (viewModel.contentCount == 0) {
            viewModel.fetchInitialData()
        }

        nextBtn.setOnClickListener {
            viewModel.contentCountIncrement()
            GlobalScope.async {
                viewModel.fetchContent(viewModel.contentCount % (viewModel.initialData.value?.ids?.size
                    ?: 0) + 1)
            }
        }
    }
}
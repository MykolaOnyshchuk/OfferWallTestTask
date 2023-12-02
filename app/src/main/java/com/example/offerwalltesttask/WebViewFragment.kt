package com.example.offerwalltesttask

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels

class WebViewFragment : Fragment() {

    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_web_view, container, false)
        val webView: WebView = view.findViewById(R.id.webView)
        webView.settings.javaScriptEnabled = true
        val webViewClient = WebViewClient()
        webView.webViewClient = webViewClient
        val webPageUrl = (viewModel.content.value as ContentWebView).url
        webView.loadUrl(webPageUrl)

        return view
    }
}
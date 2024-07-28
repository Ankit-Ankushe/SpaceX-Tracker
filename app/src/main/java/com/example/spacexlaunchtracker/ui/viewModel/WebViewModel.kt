package com.example.spacexlaunchtracker.ui.viewModel


import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

class WebViewModel(private val savedStateHandle: SavedStateHandle) : ViewModel() {
  var webViewUrl: String
    get() = savedStateHandle.get<String>("webViewUrl") ?: "https://www.spacex.com/vehicles"
    set(value) {
      savedStateHandle.set("webViewUrl", value)
    }

  var webViewState: Bundle?
    get() = savedStateHandle.get<Bundle>("webViewState")
    set(value) {
      savedStateHandle.set("webViewState", value)
    }

  val webViewClient: WebViewClient = object : WebViewClient() {
    override fun onPageFinished(view: WebView?, url: String?) {
      super.onPageFinished(view, url)
      if (url != null) {
        webViewUrl = url
      }
    }
  }
}

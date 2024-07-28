package com.example.spacexlaunchtracker

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment

class WebViewFragment : Fragment() {

  private lateinit var webView: WebView

  @SuppressLint("MissingInflatedId")
  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    val view = inflater.inflate(R.layout.fragment_web_view, container, false)
    webView = view.findViewById(R.id.webView)
    webView.webViewClient = WebViewClient()
    webView.settings.javaScriptEnabled = true
    if (savedInstanceState == null) {
      webView.loadUrl("https://www.spacex.com/vehicles")
    }
    return view
  }

  override fun onSaveInstanceState(outState: Bundle) {
    super.onSaveInstanceState(outState)
    webView.saveState(outState)
  }

  override fun onViewStateRestored(savedInstanceState: Bundle?) {
    super.onViewStateRestored(savedInstanceState)
    savedInstanceState?.let {
      webView.restoreState(it)
    }
  }
}

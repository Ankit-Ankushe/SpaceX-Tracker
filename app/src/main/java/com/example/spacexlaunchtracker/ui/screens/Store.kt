package com.example.spacexlaunchtracker.ui.screens

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.spacexlaunchtracker.ui.viewModel.CustomViewModel
import com.example.spacexlaunchtracker.ui.viewModel.WebViewModel

@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("SetJavaScriptEnabled")
@Composable
fun Store(webViewModel: WebViewModel = viewModel()){
  val context = LocalContext.current

  val webView = remember {
    WebView(context).apply {
      settings.javaScriptEnabled = true
      webViewClient = webViewModel.webViewClient

      // Restore the state if available
      webViewModel.webViewState?.let { restoreState(it) }
      loadUrl(webViewModel.webViewUrl)
    }
  }

  // Save the state when the WebView instance is remembered
  DisposableEffect(webView) {
    onDispose {
      webViewModel.webViewState = Bundle().apply {
        webView.saveState(this)
      }
    }
  }

  AndroidView(factory = { webView })
}
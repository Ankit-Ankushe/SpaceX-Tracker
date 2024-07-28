package com.example.spacexlaunchtracker.navigation

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController

@Composable
fun AppBar(
  navController: NavHostController,
  navigationIcon: String,
  onNavigationIconClick: () -> Unit,
  appTitle: String,
) {
  TopAppBar(
    backgroundColor = Color(66, 66, 66), contentColor = Color.White,
    navigationIcon = {
      if (navigationIcon == "Back") {
        IconButton(onClick = { navController.navigateUp() }) {
          Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "BackArrow")
        }
      }
    },
    title = {
      Text(text = appTitle)
    },
  )
}

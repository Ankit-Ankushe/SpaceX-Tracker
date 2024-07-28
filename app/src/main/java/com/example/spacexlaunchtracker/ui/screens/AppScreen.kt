package com.example.spacexlaunchtracker.ui.screens

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.spacexlaunchtracker.navigation.Navigation
import com.example.spacexlaunchtracker.ui.viewModel.CustomViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun  AppScreen(
  context: Context,
  customViewModel: CustomViewModel = viewModel(),
){
  val navController = rememberNavController()
  val navBackStackEntry by navController.currentBackStackEntryAsState()
  val currentRoute = navBackStackEntry?.destination?.route

  Navigation(navController, currentRoute, context, customViewModel)
}
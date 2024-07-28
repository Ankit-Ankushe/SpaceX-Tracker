package com.example.spacexlaunchtracker.ui.screens

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.spacexlaunchtracker.ui.components.LaunchItem
import com.example.spacexlaunchtracker.ui.components.ShowLoader
import com.example.spacexlaunchtracker.ui.viewModel.CustomViewModel

@Composable
fun Home(
  customViewModel: CustomViewModel,
  navController: NavHostController,
  context: Context
) {
  val appState by customViewModel._stateFlow.collectAsState()

  LaunchedEffect(Unit) {
    customViewModel.getLaunches()
  }
//  val launches = customViewModel.launches.collectAsLazyPagingItems()

  val launches = appState.launches;
  if (appState.showLoader) {
    ShowLoader()
  }else {
    LazyColumn(
      modifier = Modifier.fillMaxSize(),
      contentPadding = PaddingValues(16.dp),
      verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
      items(launches) { launch ->
        launch?.let {
          LaunchItem(launch = it, customViewModel, navController)
        }
      }

      // Add logic for endless scrolling (paging) if required
    }
  }
}
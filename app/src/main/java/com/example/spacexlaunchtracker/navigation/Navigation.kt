package com.example.spacexlaunchtracker.navigation

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.spacexlaunchtracker.ui.screens.Details
import com.example.spacexlaunchtracker.ui.screens.Home
import com.example.spacexlaunchtracker.ui.screens.Store
import com.example.spacexlaunchtracker.ui.viewModel.CustomViewModel
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Navigation(
  navController: NavHostController,
  currentRoute: String?,
  context: Context,
  customViewModel: CustomViewModel,
) {
  val scaffoldState = rememberScaffoldState()
  val scope = rememberCoroutineScope()
  val appState by customViewModel._stateFlow.collectAsState()

  var appTitle by remember { mutableStateOf("") }

  val items = listOf(
    SpacexNavItems.Home,
    SpacexNavItems.Store
  )
  var navigationIcon = "Menu"

  Scaffold(
    scaffoldState = scaffoldState,
    topBar = {
      Log.d("CurrentRouteToSetAppTile", "$currentRoute")

      if (currentRoute == "home") {
        appTitle = "Home"
      } else if (currentRoute == "details") {
        appTitle = "Details"
        navigationIcon = "Back"
      }
      AppBar(
        navController = navController,
        navigationIcon = navigationIcon,
        onNavigationIconClick = {
          scope.launch {
            scaffoldState.drawerState.open()
          }
        },
        appTitle
      )
    },
    bottomBar = {
      Log.d("current route", "$currentRoute")
      if (currentRoute == "home" || currentRoute == "store") {
        BottomNavigation(
          backgroundColor = Color(android.graphics.Color.rgb(22, 36, 48)),
          modifier = Modifier
            .height(75.dp)
        ) {
          val navBarItems: List<NavigationItem> = items

          navBarItems.forEach {
            BottomNavigationItem(selected = currentRoute == it.route,
              icon = {
                Column(
                  horizontalAlignment = Alignment.CenterHorizontally
                ) {
                  Icon(
                    imageVector = it.icons, contentDescription = null,
                    tint = if (currentRoute == it.route) androidx.compose.ui.graphics.Color.White else Color(
                      android.graphics.Color.rgb(139, 146, 152)
                    ),
                    modifier = Modifier
                      .background(
                        if (currentRoute == it.route) Color.Green else androidx.compose.ui.graphics.Color.Transparent,
                        RoundedCornerShape(10.dp)
                      )
                      .padding(4.dp)
                  )
                  Text(
                    text = it.label,
                    fontSize = 12.sp,
                    fontWeight = if (currentRoute == it.route) FontWeight.Bold else FontWeight.Normal,
                    color = if (currentRoute == it.route) androidx.compose.ui.graphics.Color.White else Color(
                      android.graphics.Color.rgb(139, 146, 152)
                    ),
                    maxLines = 1, // Set max lines to 1
                    overflow = TextOverflow.Ellipsis, // Add ellipsis for long texts
                    modifier = Modifier.padding(top = 10.dp)
                  )
                }
              },
              onClick = {
                navController.navigate(it.route) {
                  popUpTo(navController.graph.startDestinationId)
                  launchSingleTop = true
                }
                appTitle = it.label.toString()
              }
            )
          }
        }
      } else {
        Column(Modifier.height(0.dp)) {

        }
      }
    }) {
    NavigationController(navController = navController, context, customViewModel)
  }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NavigationController(
  navController: NavHostController,
  context: Context,
  customViewModel: CustomViewModel,
) {

  NavHost(
    navController = navController,
    startDestination = SpacexNavItems.Home.route
  ) {

    composable(SpacexNavItems.Home.route) {
      Home(customViewModel, navController, context)
    }
    composable(SpacexNavItems.Store.route) {
      Store()
    }
    composable(SpacexRouteItems.Details.route) {
      Details(customViewModel, navController, context)
    }
  }
}

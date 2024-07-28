package com.example.spacexlaunchtracker.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector

sealed class NavigationItem(val route: String, val label:  String, val icons: ImageVector)

sealed class SpacexNavItems(val route: String, val label: String, val icons: ImageVector) {
  object Home : NavigationItem("home","Home", Icons.Default.Home)
  object Store : NavigationItem("store","Store", Icons.Default.Store)
}
sealed class SpacexRouteItems(val route: String, val label: String, val icons: ImageVector){
  object Details : NavigationItem("details","Details",Icons.Default.DateRange)
}
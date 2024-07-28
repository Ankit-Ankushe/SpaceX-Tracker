package com.example.spacexlaunchtracker.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.spacexlaunchtracker.models.LaunchDetail
import com.example.spacexlaunchtracker.models.apiResponse.GetLaunchesApiResponse
import com.example.spacexlaunchtracker.ui.viewModel.CustomViewModel

@Composable
fun LaunchItem(
  launch: GetLaunchesApiResponse,
  customViewModel: CustomViewModel,
  navController: NavHostController,
) {

  Card(
    modifier = Modifier
      .fillMaxWidth()
      .padding(8.dp)
      .clickable {
           customViewModel.setLaunchDetails(launchDetail = LaunchDetail(
             launchDate = launch.launch_date_local,
             rocketName = launch.rocket.rocket_name,
             rocketType = launch.rocket.rocket_type,
             payloadDetails = launch.rocket.second_stage.payloads[0].payload_type,
             launchSite = launch.launch_site.site_name,
             missionName = launch.mission_name,
             mission_patch = launch.links.mission_patch!!,
             links = launch.links
           ))
        navController.navigate("details")
      }
    ,
    elevation = 4.dp
  ) {
    Row(
      modifier = Modifier
        .padding(16.dp)
    ) {
      // Image with rounded corners
      val imagePainter = rememberAsyncImagePainter(launch.links.mission_patch)
      Image(
        painter = imagePainter,
        contentDescription = null,
        modifier = Modifier
          .size(64.dp)
          .clip(CircleShape)
      )

      Spacer(modifier = Modifier.width(16.dp))

      Column {
        Text(text = launch.mission_name, style = MaterialTheme.typography.h6)
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = "Launch Year: ${launch.launch_year}", style = MaterialTheme.typography.body2)
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = "Rocket: ${launch.rocket.rocket_name}", style = MaterialTheme.typography.body2)
      }
    }
  }
}

package com.example.spacexlaunchtracker.models

import com.example.spacexlaunchtracker.models.apiResponse.Links

data class LaunchDetail(
  val missionName: String,
  val launchDate: String,
  val rocketName: String,
  val rocketType: String,
  val payloadDetails: String,
  val launchSite: String,
  val mission_patch:String,
  val links: Links
)

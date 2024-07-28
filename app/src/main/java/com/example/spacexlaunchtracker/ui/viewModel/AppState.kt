package com.example.spacexlaunchtracker.ui.viewModel

import com.example.spacexlaunchtracker.models.LaunchDetail
import com.example.spacexlaunchtracker.models.apiResponse.GetLaunchesApiResponse
import com.example.spacexlaunchtracker.models.apiResponse.Links

data class AppState(
  val launches : List<GetLaunchesApiResponse> = listOf(),
  val showLoader : Boolean = false,
  val selectedLaunch : LaunchDetail = LaunchDetail(
    launchSite = "",
    payloadDetails = "",
    launchDate = "",
    rocketName = "",
    rocketType = "",
    missionName = "",
    mission_patch = "",
    links = Links(
      mission_patch = "",
      article_link = "",
      flickr_images = listOf(),
      presskit = "",
      reddit_launch = "",
      reddit_campaign = "",
      reddit_media = "",
      reddit_recovery = "",
      video_link = "",
      wikipedia = "",
      youtube_id = "",
      mission_patch_small = ""
    )
  ),
)
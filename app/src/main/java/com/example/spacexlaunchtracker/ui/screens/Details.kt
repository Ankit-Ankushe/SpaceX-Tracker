package com.example.spacexlaunchtracker.ui.screens

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.material.Text
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.semantics.SemanticsProperties.Text
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.rememberImagePainter
import com.example.spacexlaunchtracker.ui.viewModel.CustomViewModel
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.*

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Details(
  customViewModel: CustomViewModel,
  navController: NavHostController,
  context: Context
){
  val appState by customViewModel._stateFlow.collectAsState()

  val launchDetail = appState.selectedLaunch

  Column(
    modifier = Modifier
      .padding(16.dp)
      .fillMaxSize()
      .background(Color(0xFFFAFAFA))
  ) {
    // Add the banner image at the top
    Image(
      painter = rememberImagePainter(launchDetail.mission_patch),
      contentDescription = "Launch Banner",
      contentScale = ContentScale.Fit,
      modifier = Modifier
        .fillMaxWidth()
        .height(200.dp)
        .clip(RoundedCornerShape(16.dp))
        .padding(bottom = 16.dp)
    )

    Card(
      shape = RoundedCornerShape(16.dp),
      elevation = 8.dp,
      modifier = Modifier.fillMaxWidth()
    ) {
      Column(
        modifier = Modifier
          .padding(16.dp)
          .fillMaxWidth()
      ) {
        Text(
          text = "Mission Name: ${launchDetail.missionName}",
          fontSize = 24.sp,
          fontWeight = FontWeight.Bold,
          color = MaterialTheme.colors.primary,
          modifier = Modifier.padding(bottom = 8.dp)
        )
        Text(
          text = "Launch Date: ${formatReadableDate(launchDetail.launchDate)}",
          fontSize = 18.sp,
          fontWeight = FontWeight.Medium,
          color = Color.Gray,
          modifier = Modifier.padding(bottom = 8.dp)
        )
        Text(
          text = "Rocket Details:",
          fontSize = 18.sp,
          fontWeight = FontWeight.Medium,
          color = Color.Gray,
          modifier = Modifier.padding(bottom = 8.dp)
        )
        Text(
          text = "Rocket Name: ${launchDetail.rocketName}",
          fontSize = 18.sp,
          fontWeight = FontWeight.Medium,
          color = Color.Gray,
          modifier = Modifier.padding(bottom = 8.dp)
        )
        Text(
          text = "Rocket Type: ${launchDetail.rocketType}",
          fontSize = 18.sp,
          fontWeight = FontWeight.Medium,
          color = Color.Gray,
          modifier = Modifier.padding(bottom = 8.dp)
        )
        Text(
          text = "Payload Details: ${launchDetail.payloadDetails}",
          fontSize = 18.sp,
          fontWeight = FontWeight.Medium,
          color = Color.Gray,
          modifier = Modifier.padding(bottom = 8.dp)
        )
        Text(
          text = "Launch Site: ${launchDetail.launchSite}",
          fontSize = 18.sp,
          fontWeight = FontWeight.Medium,
          color = Color.Gray,
          modifier = Modifier.padding(bottom = 8.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
          text = "Media Links:",
          fontSize = 20.sp,
          fontWeight = FontWeight.SemiBold,
          color = MaterialTheme.colors.primary,
          modifier = Modifier.padding(top = 8.dp)
        )
        ClickableText(
          text = AnnotatedString(
            text = launchDetail.links.article_link!!,
            spanStyle = SpanStyle(
              color = MaterialTheme.colors.primary,
              textDecoration = TextDecoration.Underline
            )
          ),
          onClick = {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(launchDetail.links.article_link!!))
            context.startActivity(intent)
          },
          modifier = Modifier
            .padding(bottom = 8.dp)
            .clickable(
              onClick = { /* No-op, handled by ClickableText's onClick */ },
            )
        )
        ClickableText(
          text = AnnotatedString(
            text = launchDetail.links.wikipedia!!,
            spanStyle = SpanStyle(
              color = MaterialTheme.colors.primary,
              textDecoration = TextDecoration.Underline
            )
          ),
          onClick = {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(launchDetail.links.wikipedia))
            context.startActivity(intent)
          },
          modifier = Modifier
            .padding(bottom = 8.dp)
            .clickable(
              onClick = { /* No-op, handled by ClickableText's onClick */ },
            )
        )
        ClickableText(
          text = AnnotatedString(
            text = launchDetail.links.video_link!!,
            spanStyle = SpanStyle(
              color = MaterialTheme.colors.primary,
              textDecoration = TextDecoration.Underline
            )
          ),
          onClick = {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(launchDetail.links.video_link))
            context.startActivity(intent)
          },
          modifier = Modifier
            .padding(bottom = 8.dp)
            .clickable(
              onClick = { /* No-op, handled by ClickableText's onClick */ },
            )
        )
      }
    }
  }
}

@RequiresApi(Build.VERSION_CODES.O)
fun formatReadableDate(dateString: String): String {
  val zonedDateTime = ZonedDateTime.parse(dateString)
  val formatter = DateTimeFormatter.ofPattern("MMMM d, yyyy 'at' h:mm a", Locale.ENGLISH)
  return zonedDateTime.format(formatter)
}
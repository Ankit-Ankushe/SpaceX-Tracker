package com.example.spacexlaunchtracker.data

import com.example.spacexlaunchtracker.models.apiResponse.GetLaunchesApiResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Singleton


@Singleton
public interface ApiInterface {


  @GET("/v3/launches?limit=10&offset=0")
  suspend fun getLaunches():Response<List<GetLaunchesApiResponse>>
}

object ApiInterfaceFactory {
  //Canteen App Api //
  val instance: ApiInterface by lazy {
    RetrofitHttpClient.instance.create(ApiInterface::class.java)
  }
}
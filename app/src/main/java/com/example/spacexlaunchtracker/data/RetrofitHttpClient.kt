package com.example.spacexlaunchtracker.data

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHttpClient {
  val instance : Retrofit by lazy {
    Retrofit.Builder()
      .baseUrl("https://api.spacexdata.com//")
      .addConverterFactory(GsonConverterFactory.create())
      .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
      .build()
  }
}
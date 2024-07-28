package com.example.spacexlaunchtracker.ui.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.spacexlaunchtracker.data.ApiInterfaceFactory
import com.example.spacexlaunchtracker.models.LaunchDetail
import com.google.gson.GsonBuilder
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class CustomViewModel() : ViewModel() {
  private val _uiState = MutableStateFlow(AppState())
  val _stateFlow: StateFlow<AppState> = _uiState.asStateFlow()
  private val retrofit = ApiInterfaceFactory.instance
  val gson = GsonBuilder().setPrettyPrinting().create()


  fun getLaunches(){
    _uiState.update { currentState: AppState ->
      val newState = currentState.copy(
        showLoader = true
      )
      newState
    }
    viewModelScope.launch {
      val response = retrofit.getLaunches();
      val gson = GsonBuilder().setPrettyPrinting().create()
      if(response.isSuccessful){
        val result = response.body()

        Log.d("result api",result.toString());
        if(result != null){
          _uiState.update { currentState: AppState ->
            val newState = currentState.copy(
              launches = result,
              showLoader = false
            )
            newState
          }
        }else{
          Log.d("error response", "${response.errorBody()}")
        }
      }
    }
  }

  fun setLaunchDetails(launchDetail: LaunchDetail){
    _uiState.update { currentState: AppState ->
      val newState = currentState.copy(
        selectedLaunch = launchDetail
      )
      newState
    }
  }

}
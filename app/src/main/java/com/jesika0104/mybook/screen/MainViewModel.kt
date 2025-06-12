package com.jesika0104.mybook.screen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jesika0104.mybook.network.BukuApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    init {
        retrieveData()
    }
    private fun retrieveData() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val result = BukuApi.service.getBuku()
                Log.d("MainViewModel", "Success: $result")
            } catch (e: Exception) {
                Log.e("MainViewModel", "Failure: ${e.message}")
            }
        }
    }
}
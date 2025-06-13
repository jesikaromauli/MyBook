package com.jesika0104.mybook.screen

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jesika0104.mybook.model.Buku
import com.jesika0104.mybook.network.BukuApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    var data = mutableStateOf(emptyList<Buku>())
        private set

    init {
        retrieveData()
    }
    private fun retrieveData() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                data.value = BukuApi.service.getBuku()
            } catch (e: Exception) {
                Log.e("MainViewModel", "Failure: ${e.message}")
            }
        }
    }
}
package com.example.offerwalltesttask

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val _initialData = MutableLiveData<InitialData>()
    val initialData: LiveData<InitialData> get() = _initialData

    private val _content = MutableLiveData<Content>()
    val content: LiveData<Content> get() = _content

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    fun fetchInitialData() {
        viewModelScope.launch {
            try {
                val response = ApiConfig.service.getInitialObject().execute()
                if (response.isSuccessful) {
                    val initialResult = response.body()
                    _initialData.value = initialResult
                    fetchContent(1)
                } else {
                    Log.e("ERR", "Fetch has been failed")
                }

            } catch (e: Exception) {
                _error.value = "Error fetching initial data: ${e.message}"
                Log.e("EXC", _error.value.toString())
            }
        }
    }

    suspend fun fetchContent(id: Int) {
        try {
            val call = ApiConfig.contentService.getContent(id)
            val response = call.execute()
            if (response.isSuccessful) {
                val contentResult = response.body()
                _content.value = contentResult
            } else {
                Log.e("ERR", "Fetch has been failed")
            }

        } catch (e: Exception) {
            _error.value = "Error fetching content: ${e.message}"
            Log.e("EXC", _error.value.toString())
        }
    }
}
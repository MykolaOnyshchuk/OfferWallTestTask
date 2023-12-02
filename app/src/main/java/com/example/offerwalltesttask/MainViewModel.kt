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

    val contentCount = MutableLiveData<Int>()


    fun fetchInitialData() {
        viewModelScope.launch {
            try {
                val initialService = ApiConfig.service
                val initialResult = initialService.getInitialObject()
                Log.d("DATA", initialResult.toString())
                _initialData.postValue(initialResult)
                fetchContent(1)

            } catch (e: Exception) {
                _error.value = "Error fetching initial data: ${e.message}"
                Log.e("EXC", _error.value.toString())
            }
        }
    }

    suspend fun fetchContent(id: Int) {
        try {
            val service = ApiConfig.contentService
            val contentResult = service.getContent(id)
            Log.d("DATA", contentResult.toString())
            _content.postValue(contentResult)

        } catch (e: Exception) {
            _error.value = "Error fetching content: ${e.message}"
            Log.e("EXC", _error.value.toString())
        }
    }
}
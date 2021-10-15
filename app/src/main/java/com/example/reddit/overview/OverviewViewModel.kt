package com.example.reddit.overview

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.reddit.network.RedditApi
import com.example.reddit.network.children
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class OverviewViewModel : ViewModel() {

    private val _status = MutableLiveData<String>()
    val status: LiveData<String>
        get() = _status

    private val _properties = MutableLiveData<List<children>>()
    val properties: LiveData<List<children>>
        get() = _properties

    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.IO)

    init {
        getRedditEstateProperties()
    }

    private fun getRedditEstateProperties() {
        coroutineScope.launch {
            Log.d("Yoshi", "Hola")
            var getPropertiesDeferred = RedditApi.retrofitService.getProperties()
            try {
                var data = getPropertiesDeferred.await()
                var listResult = data.data.children
                if (listResult.size > 0) {
                    _properties.postValue(listResult)
                }
            } catch (t: Throwable) {
                _status.postValue("Failure: " + t.message)
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}
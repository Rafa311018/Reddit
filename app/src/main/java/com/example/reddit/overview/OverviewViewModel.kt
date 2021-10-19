package com.example.reddit.overview

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.reddit.network.RedditChildProperty
import com.example.reddit.network.RedditApi
import com.example.reddit.network.RedditDataProperty
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class OverviewViewModel : ViewModel() {

    enum class RedditApiStatus { LOADING, ERROR, DONE }

    private val _status = MutableLiveData<RedditApiStatus>()
    val status: LiveData<RedditApiStatus>
        get() = _status

    private val _properties = MutableLiveData<List<RedditChildProperty>>()
    val properties: LiveData<List<RedditChildProperty>>
        get() = _properties

    private val _showSelftText = MutableLiveData<RedditChildProperty>()
    val showSelftText: LiveData<RedditChildProperty>
        get() = _showSelftText

    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.IO)

    init {
        getRedditEstateProperties()
    }

    private fun getRedditEstateProperties() {
        coroutineScope.launch {
            var getPropertiesDeferred = RedditApi.retrofitService.getProperties()
            var data = getPropertiesDeferred.await()
            try {
                _status.postValue(RedditApiStatus.LOADING)
                var listResult = data.Data.RedditChildProperty
                _status.postValue(RedditApiStatus.DONE)
                if (listResult.isNotEmpty()) {
                    _properties.postValue(listResult)
                }
            } catch (t: Throwable) {
                _status.postValue(RedditApiStatus.ERROR)
                _properties.postValue(ArrayList())
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    fun displaySelftText(redditChildProperty: RedditChildProperty) {
        _showSelftText.value = redditChildProperty
    }

    fun displaySelftTextComplete() {
        _showSelftText.value = null
    }
}
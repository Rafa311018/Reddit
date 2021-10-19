package com.example.reddit.network

import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize


data class RedditProperty(
    @Json(name = "data")
    val Data: Data
)


data class Data(
    @Json(name = "children")
    val RedditChildProperty: List<RedditChildProperty>
)

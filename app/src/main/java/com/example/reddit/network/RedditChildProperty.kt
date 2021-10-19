package com.example.reddit.network

import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize


data class RedditChildProperty(
    @Json(name = "data")
    val RedditDataProperty: RedditDataProperty
)
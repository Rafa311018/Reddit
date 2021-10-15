package com.example.reddit.network

import com.squareup.moshi.Json

data class RedditProperty(
    val data: data
)

data class data(
    val children: List<children>
)

data class children(
    @Json(name = "data")
    val cData: cData
)
data class cData(
//    val selftext: String,
    val author: String,
    val title: String,
    val num_comments: Int,
//    val url: String
)
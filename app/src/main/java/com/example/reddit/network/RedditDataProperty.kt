package com.example.reddit.network

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class RedditDataProperty(
    val selftext: String,
    val author: String,
    val title: String,
    val num_comments: Int,
    val url: String
): Parcelable
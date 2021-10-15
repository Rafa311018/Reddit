package com.example.reddit.overview

import android.widget.TextView
import androidx.databinding.BindingAdapter

@BindingAdapter("author")
fun getAuthor(textView: TextView, author: String?){
    author?.let {
        textView.text = author
    }
}
package com.example.reddit.overview

import android.view.View
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.reddit.network.RedditChildProperty

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data : List<RedditChildProperty>?) {
    val adapter = recyclerView.adapter as ItemAdapter
    adapter.submitList(data)
}

@BindingAdapter("author")
fun getAuthor(textView: TextView, author: String?){
    author?.let {
        textView.text = author
    }
}

@BindingAdapter("title")
fun getTitle(textView: TextView, title: String?){
    title?.let {
        textView.text = title
    }
}

@BindingAdapter("comments")
fun getComments(textView: TextView, comments: Int?){
    comments?.let {
        textView.text = comments.toString() + "Comments"
    }
}

@BindingAdapter("redditApiStatus")
fun bindStatus(statusText: TextView, status: OverviewViewModel.RedditApiStatus?){
    when(status) {
        OverviewViewModel.RedditApiStatus.LOADING -> {
            statusText.visibility = View.VISIBLE
            statusText.text = "Loading"
        }
        OverviewViewModel.RedditApiStatus.ERROR -> {
            statusText.visibility = View.VISIBLE
            statusText.text = "Error"
        }
        OverviewViewModel.RedditApiStatus.LOADING -> {
            statusText.visibility = View.GONE
        }
    }
}
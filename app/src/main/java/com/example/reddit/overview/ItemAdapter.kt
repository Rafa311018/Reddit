package com.example.reddit.overview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.reddit.databinding.ListViewItemBinding
import com.example.reddit.network.RedditChildProperty
import com.example.reddit.network.RedditDataProperty

class ItemAdapter(val onClickListener: OnClickListener) :
    ListAdapter<RedditChildProperty, ItemAdapter.RedditPropertyViewHolder>(DiffCallback) {

    class RedditPropertyViewHolder(private var binding: ListViewItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(RedditChildProperty: RedditChildProperty, onClickListener: OnClickListener) {
            binding.property = RedditChildProperty.RedditDataProperty
            binding.clickListener = onClickListener
            binding.executePendingBindings()
        }

    }

    companion object DiffCallback : DiffUtil.ItemCallback<RedditChildProperty>() {
        override fun areItemsTheSame(
            oldItem: RedditChildProperty,
            newItem: RedditChildProperty
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: RedditChildProperty,
            newItem: RedditChildProperty
        ): Boolean {
            return oldItem.RedditDataProperty.title == newItem.RedditDataProperty.title
        }

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ItemAdapter.RedditPropertyViewHolder {
        return RedditPropertyViewHolder(ListViewItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: ItemAdapter.RedditPropertyViewHolder, position: Int) {
        holder.bind(getItem(position)!!, onClickListener)
    }
}

class OnClickListener(val clickListener: (selftText: String) -> Unit) {
    fun onClickSelfText(redditDataProperty: RedditDataProperty) = clickListener(redditDataProperty.selftext)
    fun onClickWeb(redditDataProperty: RedditDataProperty) = clickListener(redditDataProperty.url)
}
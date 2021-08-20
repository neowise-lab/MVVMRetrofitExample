package com.verveteam.mvvmretrofitexample.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.verveteam.mvvmretrofitexample.data.model.Post
import com.verveteam.mvvmretrofitexample.databinding.ItemPostBinding

class PostAdapter(private val items: List<Post>)
    : RecyclerView.Adapter<PostAdapter.PostViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val binding = ItemPostBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PostViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    class PostViewHolder(private val binding: ItemPostBinding)
        : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Post) {
            binding.id.text = "${item.id}"
            binding.title.text = item.title
            binding.body.text = item.body
        }
    }
}
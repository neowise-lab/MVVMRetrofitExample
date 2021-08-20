package com.verveteam.mvvmretrofitexample.view

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.verveteam.mvvmretrofitexample.data.model.Post
import com.verveteam.mvvmretrofitexample.data.Result
import com.verveteam.mvvmretrofitexample.databinding.ActivityMainBinding
import com.verveteam.mvvmretrofitexample.viewmodel.MainViewModel
import com.verveteam.mvvmretrofitexample.viewmodel.UIState

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        setContentView(binding.root)

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.loadButton.setOnClickListener {
            viewModel.loadPosts()
        }
        observePosts()
    }

    private fun observePosts() {
        viewModel.uiState.observe(this, { uiState ->
            when(uiState) {
                UIState.Loading -> {
                    binding.recyclerView.visibility = View.INVISIBLE
                    binding.progressBar.visibility = View.VISIBLE
                }
                UIState.Complete -> {
                    binding.recyclerView.visibility = View.VISIBLE
                    binding.progressBar.visibility = View.INVISIBLE
                }
            }
        })
        viewModel.posts.observe(this, { response ->
            when(response) {
                is Result.Success -> showPosts(response.data)
                is Result.Failure -> showError()
            }
        })
    }

    private fun showPosts(posts: List<Post>) {
        val adapter = PostAdapter(posts)
        binding.recyclerView.adapter = adapter
        adapter.notifyDataSetChanged()
    }

    private fun showError() {
        Snackbar
            .make(binding.root, "Error while loading data", Snackbar.LENGTH_LONG)
            .show()
    }
}
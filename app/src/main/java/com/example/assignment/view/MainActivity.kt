package com.example.assignment.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.assignment.R
import com.example.assignment.databinding.ActivityMainBinding
import com.example.assignment.viewmodel.ArticleViewModel

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var viewModel: ArticleViewModel
    private var pageNumber = 0
    private var isLoading = false
    private lateinit var adapter: ArticleAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModel = ViewModelProvider.AndroidViewModelFactory(application).create(ArticleViewModel::class.java)
        binding.spinner.visibility = View.VISIBLE
        viewModel.fetchArticleList(pageNumber+1)
        val actionBar = supportActionBar
        actionBar?.title = "Article"
        binding.rvArticle.layoutManager = LinearLayoutManager(this)

        binding.rvArticle.addOnScrollListener(object: RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (!recyclerView.canScrollVertically(RecyclerView.FOCUS_DOWN)) {
                    if (!isLoading) {
                        binding.loading.visibility = View.VISIBLE
                        viewModel.fetchArticleList(pageNumber+1)
                    }
                    isLoading = true
                }
            }
        })

        viewModel.getArticleList().observe(this, Observer{
            binding.spinner.visibility = View.GONE
            if ((pageNumber+1) > 1) {
                isLoading = false
                binding.loading.visibility = View.GONE
                adapter.addMoreArticles(it)
                pageNumber++
            } else {
                adapter = ArticleAdapter(it, this)
                binding.rvArticle.adapter = adapter
                pageNumber++
            }
        })
    }
}

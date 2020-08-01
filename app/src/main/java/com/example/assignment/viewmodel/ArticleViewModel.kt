package com.example.assignment.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.assignment.endpoint.API.ArticleDao
import com.example.assignment.model.Article
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import rx.Subscriber
import rx.observers.Subscribers

class ArticleViewModel: ViewModel() {
    private var articleList: MutableLiveData<ArrayList<Article>> = MutableLiveData()
    private val service: ArticleDao = ArticleDao()

    fun getArticleList(): MutableLiveData<ArrayList<Article>> {
        return articleList
    }

    fun fetchArticleList(pageNumber: Int) {
        try {
            val disposable: Disposable = service.getArticleList(pageNumber, 10)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe{
                    Log.d("response", it.body().toString())
                    articleList.postValue(it.body())
                }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
package com.example.assignment.endpoint.API

import com.example.assignment.endpoint.RestAPI
import com.example.assignment.model.Article
import io.reactivex.Observable
import retrofit2.Response

class ArticleDao {

    fun getArticleList(pageNo: Int, perPage: Int): Observable<Response<ArrayList<Article>>> {
        val articleInterface = RestAPI.initializeRetrofitForAPI()?.create(ArticleAPI::class.java)
        return articleInterface!!.fetchArticleList(pageNo, perPage)
    }
}
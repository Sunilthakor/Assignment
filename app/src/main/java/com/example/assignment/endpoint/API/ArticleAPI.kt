package com.example.assignment.endpoint.API

import com.example.assignment.model.Article
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ArticleAPI {
    @GET("blogs?")
    fun fetchArticleList(@Query("page") pageNo: Int, @Query("limit") perPage: Int) : Observable<Response<ArrayList<Article>>>
}
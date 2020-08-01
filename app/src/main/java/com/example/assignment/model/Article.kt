package com.example.assignment.model

import com.google.gson.annotations.SerializedName

class Article {
    @SerializedName("id")
    var id: String = ""
    @SerializedName("createdAt")
    var createdAt: String = ""
    @SerializedName("content")
    var content: String = ""
    @SerializedName("comments")
    var comments: Double = 0.0
    @SerializedName("likes")
    var likes: Double = 0.0
    @SerializedName("media")
    var media: List<Media> = ArrayList()
    @SerializedName("user")
    var user: List<User> = ArrayList()
}
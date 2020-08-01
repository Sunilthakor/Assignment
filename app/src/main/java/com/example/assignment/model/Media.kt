package com.example.assignment.model

import com.google.gson.annotations.SerializedName

class Media {
    @SerializedName("id")
    var id: String = ""
    @SerializedName("blogId")
    var blogId: String = ""
    @SerializedName("createdAt")
    var createdAt: String = ""
    @SerializedName("image")
    var image: String = ""
    @SerializedName("title")
    var title: String = ""
    @SerializedName("url")
    var url: String = ""
}
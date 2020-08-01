package com.example.assignment.model

import com.google.gson.annotations.SerializedName

class User {
    @SerializedName("id")
    var id: String = ""
    @SerializedName("blogId")
    var blogId: String = ""
    @SerializedName("createdAt")
    var createdAt: String = ""
    @SerializedName("name")
    var name: String= ""
    @SerializedName("avatar")
    var avatar: String = ""
    @SerializedName("lastname")
    var lastName: String = ""
    @SerializedName("city")
    var city: String = ""
    @SerializedName("designation")
    var designation: String = ""
    @SerializedName("about")
    var about: String = ""
}
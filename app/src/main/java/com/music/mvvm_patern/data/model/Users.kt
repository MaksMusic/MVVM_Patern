package com.music.mvvm_patern.data.model

import com.google.gson.annotations.SerializedName

data class Users(
    @SerializedName("id") val id: Int,
    @SerializedName("login") val login: String,
    @SerializedName("avatar_url") val avatar_url: String
)
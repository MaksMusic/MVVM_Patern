package com.music.mvvm_patern.data.repository

import com.music.mvvm_patern.data.RetrofitServices

class MainRepository constructor(
    private val retrofitServices: RetrofitServices
) {
    suspend fun getAllUsers() = retrofitServices.getAllUsers()
}
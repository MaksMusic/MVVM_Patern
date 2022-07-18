package com.music.mvvm_patern.data

import com.music.mvvm_patern.data.model.Users
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface RetrofitServices {

    @GET("users")
    suspend fun getAllUsers(): Response<ArrayList<Users>>

    companion object {
        private const val BASE_URL = "https://api.github.com/"
        var retrofitServices: RetrofitServices? = null
        fun getInstance(): RetrofitServices {
            if (retrofitServices == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                retrofitServices = retrofit.create(RetrofitServices::class.java)
            }
            return retrofitServices!!
        }
    }
}
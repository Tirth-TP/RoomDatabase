package com.example.roomdatabaseassignment.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiInstance {
    private const val BASE_URL = "https://jsonplaceholder.typicode.com/"

    fun getApiData() : Retrofit{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

}
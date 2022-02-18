package com.example.roomdatabaseassignment.api

import retrofit2.Response
import retrofit2.http.GET

interface PostService {

    @GET("posts")
     suspend fun getData():Response<List<PostList>>
}
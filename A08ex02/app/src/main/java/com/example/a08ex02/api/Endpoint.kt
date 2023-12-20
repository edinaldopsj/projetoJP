package com.example.a08ex02.api

import com.example.a08ex02.models.UserResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 *  Este trecho de código pertence ao pacote api, que contém a interface Endpoint.
 */
interface Endpoint {
        @GET("api/")
        fun getUsers(
                @Query("results") results: Int
        ): Call<UserResponse>
}
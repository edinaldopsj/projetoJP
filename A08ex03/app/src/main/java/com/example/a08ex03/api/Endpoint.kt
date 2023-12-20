package com.example.a08ex03.api

import com.example.a08ex03.models.User
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

/**
 *  Este trecho de código pertence ao pacote api, que contém a interface Endpoint.
 */
interface Endpoint {

    @GET("users/{usuario}")
    fun getUsers(
        @Path("usuario") usuario: String
    ): Call<User>
}
package com.example.a08ex01.api

import com.example.a08ex01.models.User
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path


/* 
  * Este trecho de código pertence ao pacote api, que contém a interface Endpoint.
  * 
  * A interface Endpoint define as operações da API do GitHub que serão utilizadas pelo Retrofit.
  * 
  * A operação getUsers recebe um nome de usuário como parâmetro e retorna um objeto User.
  * 
  * A anotação @GET indica que a operação getUsers utiliza o método GET do protocolo HTTP.
  * 
  * A anotação @Path indica que o parâmetro usuario será utilizado para substituir o valor do parâmetro {usuario} na URL.
  * 
  * A anotação @Query indica que o parâmetro q será utilizado para substituir o valor do parâmetro q na URL.
  
*/
interface Endpoint {
        @GET("users/{usuario}")
        fun getUsers(
                @Path("usuario") usuario: String
        ): Call<User>
}
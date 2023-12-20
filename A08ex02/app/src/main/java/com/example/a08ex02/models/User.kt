package com.example.a08ex02.models

/**
  * Este trecho de código pertence ao pacote models, que contém as classes User, Name e UserResponse.
 */
data class User(val name: Name, val email: String, val phone: String)
data class Name(val title: String, val first: String, val last: String)
data class UserResponse(val results: List<User>)


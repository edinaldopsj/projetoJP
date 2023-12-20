package com.example.a08ex04.models

/**
  * Este trecho de código pertence ao pacote models, que contém a classe User.
  * 
 */
data class User(val name: Name, val email: String)
data class Name(val title: String, val first: String, val last: String)
data class UserResponse(val results: List<User>)


package com.example.a08ex05.models

import java.io.Serializable

/**
  * Este trecho de código pertence ao pacote models, que contém as classes User, Name, Location, Street e Picture.
  * 
 */
data class User(val name: Name, val email: String, val picture: Picture, val location: Location) : Serializable
data class Name(val title: String, val first: String, val last: String): Serializable
data class Location(val street: Street, val city: String, val state: String): Serializable
data class Street(val name: String, val number: Int): Serializable
data class Picture(val thumbnail: String, val large: String) : Serializable
data class UserResponse(val results: List<User>)


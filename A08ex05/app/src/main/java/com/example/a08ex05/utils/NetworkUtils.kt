package com.example.a08ex05.utils

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

/**
  * Este trecho de código pertence ao pacote util, que contém a classe NetworkUtils.
  
 */
class NetworkUtils {
    companion object{
        fun getRetrofitInstance(path: String): Retrofit{
            return Retrofit.Builder()
                .baseUrl(path)
                .addConverterFactory(MoshiConverterFactory.create())
                .build()
        }
    }
}
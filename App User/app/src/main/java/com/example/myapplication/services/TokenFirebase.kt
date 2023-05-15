package com.example.myapplication.services

import com.example.myapplication.modals.TokenFireBaseRequest
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface TokenFirebase {
    @GET("users/findOneTokenFirebase/{id}")
    suspend fun getToken(@Path("id") idToken: Int): TokenFireBaseRequest

    @GET("users/findAllTokenFireBase")
    suspend fun getAllToken(): ArrayList<TokenFireBaseRequest>

    @POST("users/saveTokenFireBase")
    suspend fun createTokenFB(@Body token: TokenFireBaseRequest): TokenFireBaseRequest


    @POST("users/updateToken/{id}")
    suspend fun updateToken(@Path("id") idToken: Int,@Body token: TokenFireBaseRequest): TokenFireBaseRequest
}
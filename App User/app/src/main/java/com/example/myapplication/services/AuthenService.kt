package com.example.myapplication.services

import com.example.myapplication.modals.LoginRequest
import retrofit2.Response
import retrofit2.http.*

interface AuthenService {
    @POST("authen/login/")
    suspend fun loginUser(@Body loginRequest: LoginRequest): LoginRequest
    @POST("authen/tokensignin/{id}")
    suspend fun signIn(@Path("id") idToken: String): LoginRequest
}
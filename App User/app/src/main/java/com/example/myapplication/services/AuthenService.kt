package com.example.myapplication.services

import com.example.myapplication.modals.LoginRequest
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthenService {
    @POST("authen/login/")
    suspend fun loginUser(@Body loginRequest: LoginRequest): LoginRequest
}
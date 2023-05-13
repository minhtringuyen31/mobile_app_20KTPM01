package com.example.appadmin.services

import retrofit2.Call
import retrofit2.http.*
import com.example.appadmin.modals.Promotion

interface PromotionService {
    @GET("promotion/")
    suspend fun getAllPromotion(): List<Promotion>

    @GET("promotion/{id}")
    suspend fun getPromotionById(@Path("id") id: Int): Promotion

    @POST("promotion/create")
    suspend fun createPromotion(@Body promotion: Promotion): Promotion

    @PUT("promotion/update/{id}")
    suspend fun updatePromotion(@Path("id") id: Int, @Body promotion: Promotion): Promotion

    @DELETE("promotion/delete/{id}")
    suspend fun deletePromotion(@Path("id") id: Int): Boolean

    @PUT("promotion/disable/{id}")
    suspend fun disablePromotion(@Path("id") id: Int): Boolean

    @PUT("promotion/enable/{id}")
    suspend fun enablePromotion(@Path("id") id: Int): Boolean
}
package com.example.myapplication.services

import com.example.myapplication.modals.Promotion
import retrofit2.http.*

interface PromotionService {
    @GET("promotions/{id}")
    suspend fun getPromotion(@Path("id") promotionId: Int):  Promotion

    @GET("promotions/")
    suspend fun getAllPromotion(): ArrayList<Promotion>

    @POST("promotions/update/{id}")
    suspend fun updatePromotion(@Path("id") promotionId: Int, @Body promotion: Promotion): Promotion

    @DELETE("promotions/delete/{id}")
    suspend fun deletePromotion(@Path("id") promotionId: Int): Int

    @POST("promotions/create/")
    suspend fun createPromotion(@Body promotion: Promotion): Promotion
}
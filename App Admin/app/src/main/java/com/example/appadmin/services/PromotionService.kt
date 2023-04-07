package com.example.appadmin.services

import retrofit2.Call
import retrofit2.http.*
import com.example.appadmin.modals.Promotion

interface PromotionService {
    @GET("promotion/{id}")
    fun getPromotion(@Path("id") promotionId: Int): Call<Promotion>

    @GET("promotion/")
    fun getAllPromotion(): Call<List<Promotion>>

    @POST("promotion/create")
    fun createPromotion(@Body promotion: Promotion): Call<Promotion>

    @PUT("promotion/update/{id}")
    fun updatePromotion(@Path("id") promotionId: Int, @Body promotion: Promotion): Call<Promotion>

    @DELETE("promotion/delete/{id}")
    fun deletePromotion(@Path("id") promotionId: Int): Call<Boolean>
}
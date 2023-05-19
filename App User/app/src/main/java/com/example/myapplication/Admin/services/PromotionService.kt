package com.example.myapplication.Admin.services

import com.example.myapplication.Admin.modals.Promotion
import com.example.myapplication.Admin.modals.countPromotion
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.*


interface PromotionService {
    @GET("promotion/")
    suspend fun getAllPromotion(): List<Promotion>

    @GET("promotion/{id}")
    suspend fun getPromotionById(@Path("id") id: Int): Promotion

    @Multipart
    @POST("promotion/create")
    suspend fun createPromotion(
        @Part("name") name: RequestBody,
        @Part("description") description: RequestBody,
        @Part("discount") discount: RequestBody,
        @Part("start_date") start_date: RequestBody,
        @Part("end_date") end_date: RequestBody,
        @Part("quantity") quantity: RequestBody,
        @Part("code") code: RequestBody,
        @Part image: MultipartBody.Part
    ): Promotion

    @Multipart
    @PUT("promotion/update/{id}")
    suspend fun updatePromotion(
        @Path("id") id: Int,
        @Part("name") name: RequestBody,
        @Part("description") description: RequestBody,
        @Part("discount") discount: RequestBody,
        @Part("start_date") start_date: RequestBody,
        @Part("end_date") end_date: RequestBody,
        @Part("quantity") quantity: RequestBody,
        @Part("code") code: RequestBody,
        @Part image: MultipartBody.Part
    ): Promotion

    @PUT("promotion/updateWithoutImage/{id}")
    suspend fun updatePromotionWithoutImage(
        @Path("id") id: Int,
        @Body promotion: Promotion
    ): Promotion

    @DELETE("promotion/delete/{id}")
    suspend fun deletePromotion(@Path("id") id: Int): Boolean

    @PUT("promotion/disable/{id}")
    suspend fun disablePromotion(@Path("id") id: Int): Boolean

    @PUT("promotion/enable/{id}")
    suspend fun enablePromotion(@Path("id") id: Int): Boolean

    @GET("promotion/count")
    suspend fun countPromotion(): countPromotion
}
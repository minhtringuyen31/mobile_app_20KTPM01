package com.example.myapplication.Admin.services

import com.example.myapplication.Admin.modals.Rating
import com.example.myapplication.Admin.modals.countRating
import retrofit2.http.*

interface RatingService {
    @GET("rating/{id}")
    suspend fun getRating(@Path("id") ratingId: Int): Rating

    @GET("rating/")
    suspend fun getAllRating(): List<Rating>

    @POST("rating/create")
    suspend fun createRating(@Body rating: Rating): Rating

    @PUT("rating/update/{id}")
    suspend fun updateRating(@Path("id") ratingId: Int, @Body rating: Rating): Rating

    @DELETE("rating/delete/{id}")
    suspend fun deleteRating(@Path("id") ratingId: Int): Boolean

    @PUT("rating/disable/{id}")
    suspend fun disableRating(@Path("id") ratingId: Int): Boolean

    @PUT("rating/enable/{id}")
    suspend fun enableRating(@Path("id") ratingId: Int): Boolean

    @GET("rating/count")
    suspend fun countRating(): countRating
}
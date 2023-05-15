package com.example.myapplication.Admin.services

import com.example.myapplication.Admin.modals.Rating
import retrofit2.Call
import retrofit2.http.*

interface RatingService {
    @GET("rating/{id}")
    fun getRating(@Path("id") ratingId: Int): Call<Rating>

    @GET("rating/")
    fun getAllRating(): Call<List<Rating>>

    @POST("rating/create")
    fun createRating(@Body rating: Rating): Call<Rating>

    @PUT("rating/update/{id}")
    fun updateRating(@Path("id") ratingId: Int, @Body rating: Rating): Call<Rating>

    @DELETE("rating/delete/{id}")
    fun deleteRating(@Path("id") ratingId: Int): Call<Boolean>
}
package com.example.myapplication.services

import com.example.myapplication.modals.Rating
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface RatingService {
    @GET("ratings/{id}")
    suspend fun getRating(@Path("id") productId: Int): ArrayList<Rating>

    @GET("rating/")
    suspend fun getAllRating(): ArrayList<Rating>

    @POST("rating/delete/{id}")
    suspend fun deleteRating(@Path("id") ratingId: Int): Rating

}
package com.example.myapplication.Admin.controllers

import com.example.myapplication.Admin.modals.Rating
import com.example.myapplication.Admin.services.RatingService
import com.example.myapplication.Admin.utils.Utils
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RatingController {
    fun getRating(id: Int): Rating? {
        var rating: Rating? = null
        try {
            val gson = GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create()
            val retrofit = Retrofit.Builder()
                .baseUrl(Utils.URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build().create(RatingService::class.java)
            val call = retrofit.getRating(id)

            call.enqueue(object : retrofit2.Callback<Rating> {
                override fun onResponse(call: Call<Rating>, response: Response<Rating>) {
                    rating = response.body()
                }

                override fun onFailure(call: Call<Rating>, t: Throwable) {
                    println("Lỗi")
                    println(t)
                }
            })
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }
        return rating
    }

    fun getAllRating(): List<Rating>? {
        var ratings: List<Rating>? = null
        try {
            val gson = GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create()
            val retrofit = Retrofit.Builder()
                .baseUrl(Utils.URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build().create(RatingService::class.java)
            val call = retrofit.getAllRating()

            call.enqueue(object : retrofit2.Callback<List<Rating>> {
                override fun onResponse(
                    call: Call<List<Rating>>,
                    response: Response<List<Rating>>
                ) {
                    ratings = response.body()
                }

                override fun onFailure(call: Call<List<Rating>>, t: Throwable) {
                    println("Lỗi")
                    println(t)
                }
            })
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }
        return ratings
    }

    fun createRating(ratingNew: Rating): Rating? {
        var rating: Rating? = null
        try {
            val gson = GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create()
            val retrofit = Retrofit.Builder()
                .baseUrl(Utils.URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build().create(RatingService::class.java)
            val call = retrofit.createRating(ratingNew)

            call.enqueue(object : retrofit2.Callback<Rating> {
                override fun onResponse(call: Call<Rating>, response: Response<Rating>) {
                    rating = response.body()
                }

                override fun onFailure(call: Call<Rating>, t: Throwable) {
                    println("Lỗi")
                    println(t)
                }
            })
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }
        return rating
    }

    fun updateRating(id: Int, ratingNew: Rating): Rating? {
        var rating: Rating? = null
        try {
            val gson = GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create()
            val retrofit = Retrofit.Builder()
                .baseUrl(Utils.URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build().create(RatingService::class.java)
            val call = retrofit.updateRating(id, ratingNew)

            call.enqueue(object : retrofit2.Callback<Rating> {
                override fun onResponse(call: Call<Rating>, response: Response<Rating>) {
                    rating = response.body()
                }

                override fun onFailure(call: Call<Rating>, t: Throwable) {
                    println("Lỗi")
                    println(t)
                }
            })
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }
        return rating
    }

    fun deleteRating(id: Int): Boolean {
        var status = false
        try {
            val gson = GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create()
            val retrofit = Retrofit.Builder()
                .baseUrl(Utils.URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build().create(RatingService::class.java)
            val call = retrofit.deleteRating(id)

            call.enqueue(object : retrofit2.Callback<Boolean> {
                override fun onResponse(call: Call<Boolean>, response: Response<Boolean>) {
                    status = response.body() == true
                }

                override fun onFailure(call: Call<Boolean>, t: Throwable) {
                    println("Lỗi")
                    println(t)
                }
            })
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }
        return status
    }
}
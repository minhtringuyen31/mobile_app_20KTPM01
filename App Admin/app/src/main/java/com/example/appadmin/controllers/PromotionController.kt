package com.example.appadmin.controllers

import com.example.appadmin.modals.Promotion
import com.example.appadmin.services.PromotionService
import com.example.appadmin.utils.Utils
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PromotionController {
    fun getPromotion(id: Int): Promotion? {
        var promotion: Promotion? = null
        try {
            val gson = GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create()
            val retrofit = Retrofit.Builder()
                .baseUrl(Utils.URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build().create(PromotionService::class.java)
            val call = retrofit.getPromotion(id)

            call.enqueue(object : retrofit2.Callback<Promotion> {
                override fun onResponse(call: Call<Promotion>, response: Response<Promotion>) {
                    promotion = response.body()
                }

                override fun onFailure(call: Call<Promotion>, t: Throwable) {
                    println("Lỗi")
                    println(t)
                }
            })
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }
        return promotion
    }

    fun getAllPromotion(): List<Promotion>? {
        var promotions: List<Promotion>? = null
        try {
            val gson = GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create()
            val retrofit = Retrofit.Builder()
                .baseUrl(Utils.URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build().create(PromotionService::class.java)
            val call = retrofit.getAllPromotion()

            call.enqueue(object : retrofit2.Callback<List<Promotion>> {
                override fun onResponse(
                    call: Call<List<Promotion>>,
                    response: Response<List<Promotion>>
                ) {
                    promotions = response.body()
                }

                override fun onFailure(call: Call<List<Promotion>>, t: Throwable) {
                    println("Lỗi")
                    println(t)
                }
            })
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }
        return promotions
    }

    fun createPromotion(promotionNew: Promotion): Promotion? {
        var promotion: Promotion? = null
        try {
            val gson = GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create()
            val retrofit = Retrofit.Builder()
                .baseUrl(Utils.URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build().create(PromotionService::class.java)
            val call = retrofit.createPromotion(promotionNew)

            call.enqueue(object : retrofit2.Callback<Promotion> {
                override fun onResponse(call: Call<Promotion>, response: Response<Promotion>) {
                    promotion = response.body()
                }

                override fun onFailure(call: Call<Promotion>, t: Throwable) {
                    println("Lỗi")
                    println(t)
                }
            })
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }
        return promotion
    }

    fun updatePromotion(id: Int, promotionNew: Promotion): Promotion? {
        var promotion: Promotion? = null
        try {
            val gson = GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create()
            val retrofit = Retrofit.Builder()
                .baseUrl(Utils.URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build().create(PromotionService::class.java)
            val call = retrofit.updatePromotion(id, promotionNew)

            call.enqueue(object : retrofit2.Callback<Promotion> {
                override fun onResponse(call: Call<Promotion>, response: Response<Promotion>) {
                    promotion = response.body()
                }

                override fun onFailure(call: Call<Promotion>, t: Throwable) {
                    println("Lỗi")
                    println(t)
                }
            })
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }
        return promotion
    }

    fun deletePromotion(id: Int): Boolean {
        var status = false
        try {
            val gson = GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create()
            val retrofit = Retrofit.Builder()
                .baseUrl(Utils.URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build().create(PromotionService::class.java)
            val call = retrofit.deletePromotion(id)

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
package com.example.appadmin.services

import retrofit2.Call
import retrofit2.http.*
import com.example.appadmin.modals.Product

interface ProductService {
    @GET("product/{id}")
    fun getProduct(@Path("id") productId: Int): Call<Product>

    @GET("product/")
    fun getAllProduct(): Call<List<Product>>

    @POST("product/create")
    fun createProduct(@Body product: Product): Call<Product>

    @PUT("product/update/{id}")
    fun updateProduct(@Path("id") productId: Int, @Body product: Product): Call<Product>

    @DELETE("product/delete/{id}")
    fun deleteProduct(@Path("id") productId: Int): Call<Boolean>
}
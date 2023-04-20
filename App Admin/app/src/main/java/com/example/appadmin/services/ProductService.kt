package com.example.appadmin.services

import retrofit2.Call
import retrofit2.http.*
import com.example.appadmin.modals.Product

interface ProductService {
    @GET("product/{id}")
    suspend fun getProduct(@Path("id") productId: Int): Product

    @GET("product/")
    suspend fun getAllProduct(): List<Product>

    @POST("product/create")
    suspend fun createProduct(@Body product: Product): Product

    @PUT("product/update/{id}")
    suspend fun updateProduct(@Path("id") productId: Int, @Body product: Product): Product

    @DELETE("product/delete/{id}")
    suspend fun deleteProduct(@Path("id") productId: Int): Boolean

    @PUT("product/disable/{id}")
    suspend fun disableProduct(@Path("id") productId: Int):Boolean

    @PUT("product/enable/{id}")
    suspend fun enableProduct(@Path("id") productId: Int): Boolean

    @PUT("product/available/{id}")
    suspend fun availableProduct(@Path("id") productId: Int): Boolean

    @PUT("product/unavailable/{id}")
    suspend fun unavailableProduct(@Path("id") productId: Int): Boolean
}
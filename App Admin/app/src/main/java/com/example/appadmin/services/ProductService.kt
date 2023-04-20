package com.example.appadmin.services

import retrofit2.Call
import retrofit2.http.*
import com.example.appadmin.modals.Product

interface ProductService {
    @GET("product/{id}")
    fun getProduct(@Path("id") productId: Int): Product

    @GET("product/")
    fun getAllProduct(): List<Product>

    @POST("product/create")
    fun createProduct(@Body product: Product): Product

    @PUT("product/update/{id}")
    fun updateProduct(@Path("id") productId: Int, @Body product: Product): Product

    @DELETE("product/delete/{id}")
    fun deleteProduct(@Path("id") productId: Int): Boolean

    @PUT("product/disable/{id}")
    fun disableProduct(@Path("id") productId: Int):Boolean

    @PUT("product/enable/{id}")
    fun enableProduct(@Path("id") productId: Int): Boolean

    @PUT("product/available/{id}")
    fun availableProduct(@Path("id") productId: Int): Boolean

    @PUT("product/unavailable/{id}")
    fun unavailableProduct(@Path("id") productId: Int): Boolean
}
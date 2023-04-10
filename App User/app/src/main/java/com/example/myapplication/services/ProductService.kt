package com.example.myapplication.services

import com.example.myapplication.modals.Category
import com.example.myapplication.modals.Product
import retrofit2.http.*

interface ProductService {
    @GET("products/{id}")
    suspend fun getProduct(@Path("id") productId: Int): Product

    @GET("products/")
    suspend fun getAllProduct(): ArrayList<Product>

    @GET("categories/")
    suspend fun getAllCategory(): ArrayList<Category>

    @POST("products/update/{id}")
    suspend fun updateProduct(@Path("id") productId: Int, @Body product: Product): Product

    @DELETE("products/delete/{id}")
    suspend fun deleteProduct(@Path("id") productId: Int): Int

    @POST("products/create/")
    suspend fun createProduct(@Body product: Product): Product
}
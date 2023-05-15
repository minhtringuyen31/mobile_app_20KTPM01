package com.example.myapplication.Admin.services

import com.example.myapplication.Admin.modals.Product
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.*


interface ProductService {
    @GET("product/{id}")
    suspend fun getProduct(@Path("id") productId: Int): Product

    @GET("product/")
    suspend fun getAllProduct(): List<Product>

    @Multipart
    @POST("product/create")
    suspend fun createProduct(
        @Part("name") name: RequestBody,
        @Part("description") description: RequestBody,
        @Part("size") size: RequestBody,
        @Part("price_S") price_S: RequestBody,
        @Part("price_M") price_M: RequestBody,
        @Part("price_L") price_L: RequestBody,
        @Part("status") status: RequestBody,
        @Part("category_id") category_id: RequestBody,
        @Part("update_date") update_date: RequestBody,
        @Part("release_date") release_date: RequestBody,
        @Part("sales") sales: RequestBody,
        @Part image: MultipartBody.Part
    ): Product

    @Multipart
    @PUT("product/update/{id}")
    suspend fun updateProduct(
        @Path("id") productId: Int,
        @Part("name") name: RequestBody,
        @Part("description") description: RequestBody,
        @Part("size") size: RequestBody,
        @Part("price_S") price_S: RequestBody,
        @Part("price_M") price_M: RequestBody,
        @Part("price_L") price_L: RequestBody,
        @Part("category_id") category_id: RequestBody,
        @Part("update_date") update_date: RequestBody,
        @Part image: MultipartBody.Part
    ): Product

    @PUT("product/updateWithoutImage/{id}")
    suspend fun updateWithoutImage(
        @Path("id") productId: Int,
        @Body product: Product
    ): Product

    @DELETE("product/delete/{id}")
    suspend fun deleteProduct(@Path("id") productId: Int): Boolean

    @PUT("product/disable/{id}")
    suspend fun disableProduct(@Path("id") productId: Int): Boolean

    @PUT("product/enable/{id}")
    suspend fun enableProduct(@Path("id") productId: Int): Boolean

    @PUT("product/available/{id}")
    suspend fun availableProduct(@Path("id") productId: Int): Boolean

    @PUT("product/unavailable/{id}")
    suspend fun unavailableProduct(@Path("id") productId: Int): Boolean
}
package com.example.myapplication.services

import com.example.myapplication.modals.Category
import com.example.myapplication.modals.FavProductItem
import com.example.myapplication.modals.Product
import retrofit2.http.*

interface ProductService {
    @GET("products/{id}")
    suspend fun getProduct(@Path("id") productId: Int): Product

    @GET("products/{categoryId}")
    suspend fun getProductByCategory(@Path("categoryId") categoryId: Int) : ArrayList<Product>

    @GET("products/")
    suspend fun getAllProduct(): ArrayList<Product>
    @GET("products/sales")
    suspend fun getAllProductSale(): ArrayList<Product>
    @GET("products/rating/{productId}")
    suspend fun getRating(@Path("productId") productId: Int) : Float

    @GET("categories/")
    suspend fun getAllCategory(): ArrayList<Category>

    @POST("products/update/{id}")
    suspend fun updateProduct(@Path("id") productId: Int, @Body product: Product): Product

    @DELETE("products/delete/{id}")
    suspend fun deleteProduct(@Path("id") productId: Int): Int

    @POST("products/create/")
    suspend fun createProduct(@Body product: Product): Product

    @GET("products/favorite/{userId}")
    suspend fun getAllFavProduct(@Path("userId") userId:Int): ArrayList<Product>

    @POST("products/favorite/add")
    suspend fun addFavProduct(@Body favProduct: FavProductItem): Product

    @POST("products/favorite/remove")
    suspend fun removeFavProduct(@Body favProduct: FavProductItem): Boolean
    @GET("products/favorite/check")
    suspend fun findAFavProduct(@Query("userId") userId: Int, @Query("productId") productId: Int): Boolean
}
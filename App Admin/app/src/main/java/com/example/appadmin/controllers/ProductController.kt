package com.example.appadmin.controllers

import com.example.appadmin.modals.Product
import com.example.appadmin.services.ProductService
import com.example.appadmin.utils.Utils
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ProductController {
    fun getProduct(id: Int): Product? {
        var product: Product? = null
        try {
            val gson = GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create()
            val retrofit = Retrofit.Builder()
                .baseUrl(Utils.URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build().create(ProductService::class.java)
            val call = retrofit.getProduct(id)

            call.enqueue(object : retrofit2.Callback<Product> {
                override fun onResponse(call: Call<Product>, response: Response<Product>) {
                    product = response.body()
                }

                override fun onFailure(call: Call<Product>, t: Throwable) {
                    println("Lỗi")
                    println(t)
                }
            })
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }
        return product
    }

    fun getAllProduct(): List<Product>? {
        var products: List<Product>? = null
        try {
            val gson = GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create()
            val retrofit = Retrofit.Builder()
                .baseUrl(Utils.URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build().create(ProductService::class.java)
            val call = retrofit.getAllProduct()

            call.enqueue(object : retrofit2.Callback<List<Product>> {
                override fun onResponse(
                    call: Call<List<Product>>,
                    response: Response<List<Product>>
                ) {
                    products = response.body()
                }

                override fun onFailure(call: Call<List<Product>>, t: Throwable) {
                    println("Lỗi")
                    println(t)
                }
            })
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }
        return products
    }

    fun createProduct(productNew: Product): Product? {
        var product: Product? = null
        try {
            val gson = GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create()
            val retrofit = Retrofit.Builder()
                .baseUrl(Utils.URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build().create(ProductService::class.java)
            val call = retrofit.createProduct(productNew)

            call.enqueue(object : retrofit2.Callback<Product> {
                override fun onResponse(call: Call<Product>, response: Response<Product>) {
                    product = response.body()
                }

                override fun onFailure(call: Call<Product>, t: Throwable) {
                    println("Lỗi")
                    println(t)
                }
            })
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }
        return product
    }

    fun updateProduct(id: Int, productNew: Product): Product? {
        var product: Product? = null
        try {
            val gson = GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create()
            val retrofit = Retrofit.Builder()
                .baseUrl(Utils.URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build().create(ProductService::class.java)
            val call = retrofit.updateProduct(id, productNew)

            call.enqueue(object : retrofit2.Callback<Product> {
                override fun onResponse(call: Call<Product>, response: Response<Product>) {
                    product = response.body()
                }

                override fun onFailure(call: Call<Product>, t: Throwable) {
                    println("Lỗi")
                    println(t)
                }
            })
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }
        return product
    }

    fun deleteProduct(id: Int): Boolean {
        var status = false
        try {
            val gson = GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create()
            val retrofit = Retrofit.Builder()
                .baseUrl(Utils.URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build().create(ProductService::class.java)
            val call = retrofit.deleteProduct(id)

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
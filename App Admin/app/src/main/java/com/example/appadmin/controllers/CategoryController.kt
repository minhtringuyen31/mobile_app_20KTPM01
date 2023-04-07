package com.example.appadmin.controllers

import com.example.appadmin.modals.Category
import com.example.appadmin.services.CategoryService
import com.example.appadmin.utils.Utils
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CategoryController {
    fun getCategory(id: Int): Category? {
        var category: Category? = null
        try {
            val gson = GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create()
            val retrofit = Retrofit.Builder()
                .baseUrl(Utils.URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build().create(CategoryService::class.java)
            val call = retrofit.getCategory(id)

            call.enqueue(object : retrofit2.Callback<Category> {
                override fun onResponse(call: Call<Category>, response: Response<Category>) {
                    category = response.body()
                }

                override fun onFailure(call: Call<Category>, t: Throwable) {
                    println("Lỗi")
                    println(t)
                }
            })
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }
        return category
    }

    fun getAllCategory(): List<Category>? {
        var categories: List<Category>? = null
        try {
            val gson = GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create()
            val retrofit = Retrofit.Builder()
                .baseUrl(Utils.URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build().create(CategoryService::class.java)
            val call = retrofit.getAllCategory()

            call.enqueue(object : retrofit2.Callback<List<Category>> {
                override fun onResponse(
                    call: Call<List<Category>>,
                    response: Response<List<Category>>
                ) {
                    categories = response.body()
                }

                override fun onFailure(call: Call<List<Category>>, t: Throwable) {
                    println("Lỗi")
                    println(t)
                }
            })
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }
        return categories
    }

    fun createCategory(categoryNew: Category): Category? {
        var category: Category? = null
        try {
            val gson = GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create()
            val retrofit = Retrofit.Builder()
                .baseUrl(Utils.URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build().create(CategoryService::class.java)
            val call = retrofit.createCategory(categoryNew)

            call.enqueue(object : retrofit2.Callback<Category> {
                override fun onResponse(call: Call<Category>, response: Response<Category>) {
                    category = response.body()
                }

                override fun onFailure(call: Call<Category>, t: Throwable) {
                    println("Lỗi")
                    println(t)
                }
            })
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }
        return category
    }

    fun updateCategory(id: Int, categoryNew: Category): Category? {
        var category: Category? = null
        try {
            val gson = GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create()
            val retrofit = Retrofit.Builder()
                .baseUrl(Utils.URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build().create(CategoryService::class.java)
            val call = retrofit.updateCategory(id, categoryNew)

            call.enqueue(object : retrofit2.Callback<Category> {
                override fun onResponse(call: Call<Category>, response: Response<Category>) {
                    category = response.body()
                }

                override fun onFailure(call: Call<Category>, t: Throwable) {
                    println("Lỗi")
                    println(t)
                }
            })
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }
        return category
    }

    fun deleteCategory(id: Int): Boolean {
        var status = false
        try {
            val gson = GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create()
            val retrofit = Retrofit.Builder()
                .baseUrl(Utils.URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build().create(CategoryService::class.java)
            val call = retrofit.deleteCategory(id)

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
package com.example.appadmin.controllers

import com.example.appadmin.modals.User
import com.example.appadmin.services.UserService
import com.example.appadmin.utils.Utils
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class UserController {
    fun getUser(id: Int): User? {
        var user: User? = null
        try {
            val gson = GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create()
            val retrofit = Retrofit.Builder()
                .baseUrl(Utils.URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build().create(UserService::class.java)
            val call = retrofit.getUser(id)

            call.enqueue(object : retrofit2.Callback<User> {
                override fun onResponse(call: Call<User>, response: Response<User>) {
                    user = response.body()
                }

                override fun onFailure(call: Call<User>, t: Throwable) {
                    println("Lỗi")
                    println(t)
                }
            })
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }
        return user
    }

    fun getAllUser(): List<User>? {
        var users: List<User>? = null
        try {
            val gson = GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create()
            val retrofit = Retrofit.Builder()
                .baseUrl(Utils.URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build().create(UserService::class.java)
            val call = retrofit.getAllUser()

            call.enqueue(object : retrofit2.Callback<List<User>> {
                override fun onResponse(
                    call: Call<List<User>>,
                    response: Response<List<User>>
                ) {
                    users = response.body()
                }

                override fun onFailure(call: Call<List<User>>, t: Throwable) {
                    println("Lỗi")
                    println(t)
                }
            })
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }
        return users
    }

    fun createUser(userNew: User): User? {
        var user: User? = null
        try {
            val gson = GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create()
            val retrofit = Retrofit.Builder()
                .baseUrl(Utils.URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build().create(UserService::class.java)
            val call = retrofit.createUser(userNew)

            call.enqueue(object : retrofit2.Callback<User> {
                override fun onResponse(call: Call<User>, response: Response<User>) {
                    user = response.body()
                }

                override fun onFailure(call: Call<User>, t: Throwable) {
                    println("Lỗi")
                    println(t)
                }
            })
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }
        return user
    }

    fun updateUser(id: Int, userNew: User): User? {
        var user: User? = null
        try {
            val gson = GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create()
            val retrofit = Retrofit.Builder()
                .baseUrl(Utils.URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build().create(UserService::class.java)
            val call = retrofit.updateUser(id, userNew)

            call.enqueue(object : retrofit2.Callback<User> {
                override fun onResponse(call: Call<User>, response: Response<User>) {
                    user = response.body()
                }

                override fun onFailure(call: Call<User>, t: Throwable) {
                    println("Lỗi")
                    println(t)
                }
            })
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }
        return user
    }

    fun deleteUser(id: Int): Boolean {
        var status = false
        try {
            val gson = GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create()
            val retrofit = Retrofit.Builder()
                .baseUrl(Utils.URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build().create(UserService::class.java)
            val call = retrofit.deleteUser(id)

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
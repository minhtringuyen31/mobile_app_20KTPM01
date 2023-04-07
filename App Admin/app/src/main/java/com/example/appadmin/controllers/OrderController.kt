package com.example.appadmin.controllers

import com.example.appadmin.modals.Order
import com.example.appadmin.services.OrderService
import com.example.appadmin.utils.Utils
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class OrderController {
    fun getOrder(id: Int): Order? {
        var order: Order? = null
        try {
            val gson = GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create()
            val retrofit = Retrofit.Builder()
                .baseUrl(Utils.URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build().create(OrderService::class.java)
            val call = retrofit.getOrder(id)

            call.enqueue(object : retrofit2.Callback<Order> {
                override fun onResponse(call: Call<Order>, response: Response<Order>) {
                    order = response.body()
                }

                override fun onFailure(call: Call<Order>, t: Throwable) {
                    println("Lỗi")
                    println(t)
                }
            })
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }
        return order
    }

    fun getAllOrder(): List<Order>? {
        var orders: List<Order>? = null
        try {
            val gson = GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create()
            val retrofit = Retrofit.Builder()
                .baseUrl(Utils.URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build().create(OrderService::class.java)
            val call = retrofit.getAllOrder()

            call.enqueue(object : retrofit2.Callback<List<Order>> {
                override fun onResponse(
                    call: Call<List<Order>>,
                    response: Response<List<Order>>
                ) {
                    orders = response.body()
                }

                override fun onFailure(call: Call<List<Order>>, t: Throwable) {
                    println("Lỗi")
                    println(t)
                }
            })
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }
        return orders
    }

    fun createOrder(orderNew: Order): Order? {
        var order: Order? = null
        try {
            val gson = GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create()
            val retrofit = Retrofit.Builder()
                .baseUrl(Utils.URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build().create(OrderService::class.java)
            val call = retrofit.createOrder(orderNew)

            call.enqueue(object : retrofit2.Callback<Order> {
                override fun onResponse(call: Call<Order>, response: Response<Order>) {
                    order = response.body()
                }

                override fun onFailure(call: Call<Order>, t: Throwable) {
                    println("Lỗi")
                    println(t)
                }
            })
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }
        return order
    }

    fun updateOrder(id: Int, orderNew: Order): Order? {
        var order: Order? = null
        try {
            val gson = GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create()
            val retrofit = Retrofit.Builder()
                .baseUrl(Utils.URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build().create(OrderService::class.java)
            val call = retrofit.updateOrder(id, orderNew)

            call.enqueue(object : retrofit2.Callback<Order> {
                override fun onResponse(call: Call<Order>, response: Response<Order>) {
                    order = response.body()
                }

                override fun onFailure(call: Call<Order>, t: Throwable) {
                    println("Lỗi")
                    println(t)
                }
            })
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }
        return order
    }

    fun deleteOrder(id: Int): Boolean {
        var status = false
        try {
            val gson = GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create()
            val retrofit = Retrofit.Builder()
                .baseUrl(Utils.URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build().create(OrderService::class.java)
            val call = retrofit.deleteOrder(id)

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
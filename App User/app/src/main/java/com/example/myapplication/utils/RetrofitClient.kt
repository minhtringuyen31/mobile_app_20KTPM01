package com.example.myapplication.utils

import retrofit2.Retrofit

import retrofit2.converter.gson.GsonConverterFactory


class RetrofitClient private constructor() {
    init {
        // Khởi tạo Retrofit
        retrofit = Retrofit.Builder()
            .baseUrl("http://10.0.2.2:3000/api/") // Thay thế URL base của API
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    companion object {
        private var retrofit: Retrofit? = null

        // Tạo một phiên bản Retrofit mới nếu chưa được khởi tạo trước đó
        @get:Synchronized
        val instance: Retrofit?
            get() {
                if (retrofit == null) {
                    // Tạo một phiên bản Retrofit mới nếu chưa được khởi tạo trước đó
                    RetrofitClient()
                }
                return retrofit
            }
    }
}
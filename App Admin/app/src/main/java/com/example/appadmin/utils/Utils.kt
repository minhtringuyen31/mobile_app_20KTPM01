package com.example.appadmin.utils

import androidx.appcompat.app.AppCompatActivity
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Utils : AppCompatActivity() {
    companion object {
        const val URL = "http://10.0.2.2:3000/api/admin/"

        fun getRetrofit(): Retrofit {
            val gson = GsonBuilder()
                .setDateFormat("yyyy-MM-dd HH:mm:ss")
                .create()
            val retrofit = Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
            return retrofit
        }
    }
}
package com.example.myapplication.services

import com.example.myapplication.modals.Notification
import com.example.myapplication.modals.Order
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface NotificationService {
    @GET("notifications/{userId}")
    suspend fun getNotification(@Path("userId") userId: Int): ArrayList<Notification>

    @POST("notifications/")
    suspend fun postNotification(@Body notification: Notification): Boolean
}
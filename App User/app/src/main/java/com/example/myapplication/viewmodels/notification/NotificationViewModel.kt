package com.example.myapplication.viewmodels.notification

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.modals.Notification
import com.example.myapplication.services.NotificationService
import com.example.myapplication.utils.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit

class NotificationViewModel: ViewModel() {
    private val _notifications = MutableLiveData<ArrayList<Notification>>()
    val notifications: LiveData<ArrayList<Notification>> = _notifications

    private val _success = MutableLiveData<Boolean>()
    val success: LiveData<Boolean> = _success
    private val retrofit: Retrofit = RetrofitClient.instance!!
    fun getNotification(userId: Int){
        viewModelScope.launch(Dispatchers.Main) {
            try {
                val response =
                    retrofit.create(NotificationService::class.java).getNotification(userId)
                _notifications.postValue(response)

            } catch (e: Exception) {
                println("View : $e")
            }
        }
    }

    fun postNotification(notification: Notification){
        viewModelScope.launch(Dispatchers.Main) {
            try {
                val response =
                    retrofit.create(NotificationService::class.java).postNotification(notification)
                _success.postValue(response)
            } catch (e: Exception) {
                println("View : $e")
            }
        }
    }
}
package com.example.myapplication.viewmodels.user

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.modals.User
import com.example.myapplication.services.UserService
import com.example.myapplication.utils.Utils
import kotlinx.coroutines.launch

class UserViewModel:ViewModel() {
    private val _user = MutableLiveData<User>() // theo doi bien minh chi dinh , muon render UI
    val user: LiveData<User> = _user // du lieu truc tiep muon render ra ngoai

    fun getUser(id:Int) {
        viewModelScope.launch {
            try {
                val response = Utils.getRetrofit().create(UserService::class.java).getUser(id);
                println("View: $response")
                _user.value=response
            } catch (e: Exception) {
                // handle error
            }
        }
    }

}
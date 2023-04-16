package com.example.appadmin.controllers

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appadmin.modals.User
import com.example.appadmin.services.UserService
import com.example.appadmin.utils.Utils
import kotlinx.coroutines.launch
import retrofit2.create

class UserController : ViewModel() {
    fun getUser(id: Int): LiveData<User> {
        val _user = MutableLiveData<User>()
        viewModelScope.launch {
            try {
                val response = Utils.getRetrofit().create(UserService::class.java).getUser(id)
                _user.value = response
            } catch (e: Exception) {

            }
        }
        return _user
    }

    fun getAllUser(): LiveData<List<User>> {
        val _user = MutableLiveData<List<User>>()
        viewModelScope.launch {
            try {
                val response = Utils.getRetrofit().create(UserService::class.java).getAllUser()
                _user.value = response
                println(response)
            } catch (e: Exception) {
                // handle error
            }
        }
        return _user
    }

    fun createUser(userNew: User): LiveData<User> {
        val _user = MutableLiveData<User>()
        viewModelScope.launch {
            try {
                val response =
                    Utils.getRetrofit().create(UserService::class.java).createUser(userNew)
                _user.value = response
            } catch (e: Exception) {

            }
        }
        return _user
    }

    fun updateUser(id: Int, userNew: User): LiveData<User> {
        val _user = MutableLiveData<User>()
        viewModelScope.launch {
            try {
                val response =
                    Utils.getRetrofit().create(UserService::class.java).updateUser(id, userNew)
                _user.value = response
            } catch (e: Exception) {

            }
        }
        return _user
    }

    fun deleteUser(id: Int): LiveData<Boolean> {
        val _user = MutableLiveData<Boolean>()
        viewModelScope.launch {
            try {
                val response = Utils.getRetrofit().create(UserService::class.java).deleteUser(id)
                _user.value = response
                println(response)
            } catch (e: Exception) {

            }
        }
        return _user
    }
}
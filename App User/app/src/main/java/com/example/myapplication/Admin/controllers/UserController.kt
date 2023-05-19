package com.example.myapplication.Admin.controllers

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.Admin.modals.User
import com.example.myapplication.Admin.modals.countUser
import com.example.myapplication.Admin.services.UserService
import com.example.myapplication.Admin.utils.Utils
import kotlinx.coroutines.launch

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
            } catch (e: Exception) {

            }
        }
        return _user
    }

    fun disableUser(id: Int): LiveData<Boolean> {
        val _user = MutableLiveData<Boolean>()
        viewModelScope.launch {
            try {
                val response = Utils.getRetrofit().create(UserService::class.java).disableUser(id)
                _user.value = response
            } catch (e: Exception) {

            }
        }
        return _user
    }

    fun enableUser(id: Int): LiveData<Boolean> {
        val _user = MutableLiveData<Boolean>()
        viewModelScope.launch {
            try {
                val response = Utils.getRetrofit().create(UserService::class.java).enableUser(id)
                _user.value = response
            } catch (e: Exception) {

            }
        }
        return _user
    }

    fun countUser(): LiveData<countUser> {
        val _user = MutableLiveData<countUser>()
        viewModelScope.launch {
            try {
                val response = Utils.getRetrofit().create(UserService::class.java).countUser()
                _user.value = response
            } catch (e: Exception) {
                // handle error
            }
        }
        return _user
    }
}
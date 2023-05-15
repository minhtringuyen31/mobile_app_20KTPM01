package com.example.myapplication.Admin.controllers

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.Admin.modals.Topping
import com.example.myapplication.Admin.services.ToppingService
import com.example.myapplication.Admin.utils.Utils
import kotlinx.coroutines.launch


class ToppingController : ViewModel() {
    fun getTopping(id: Int): LiveData<Topping> {
        val _topping = MutableLiveData<Topping>()
        viewModelScope.launch {
            try {
                val response =
                    Utils.getRetrofit().create(ToppingService::class.java).getTopping(id)
                _topping.value = response
            } catch (e: Exception) {

            }
        }
        return _topping
    }

    fun getAllTopping(): LiveData<List<Topping>> {
        val _topping = MutableLiveData<List<Topping>>()
        viewModelScope.launch {
            try {
                val response =
                    Utils.getRetrofit().create(ToppingService::class.java).getAllTopping()
                _topping.value = response
            } catch (e: Exception) {

            }
        }
        return _topping
    }

    fun createTopping(toppingNew: Topping): LiveData<Topping> {
        val _topping = MutableLiveData<Topping>()
        viewModelScope.launch {
            try {
                val response =
                    Utils.getRetrofit().create(ToppingService::class.java).createTopping(toppingNew)
                _topping.value = response
            } catch (e: Exception) {

            }
        }
        return _topping
    }

    fun updateTopping(id: Int, toppingNew: Topping): LiveData<Topping> {
        val _topping = MutableLiveData<Topping>()
        viewModelScope.launch {
            try {
                val response =
                    Utils.getRetrofit().create(ToppingService::class.java)
                        .updateTopping(id, toppingNew)
                _topping.value = response
            } catch (e: Exception) {

            }
        }
        return _topping
    }

    fun deleteTopping(id: Int): LiveData<Boolean> {
        val _topping = MutableLiveData<Boolean>()
        viewModelScope.launch {
            try {
                val response =
                    Utils.getRetrofit().create(ToppingService::class.java).deleteTopping(id)
                _topping.value = response
            } catch (e: Exception) {

            }
        }
        return _topping
    }
}
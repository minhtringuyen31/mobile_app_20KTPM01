package com.example.myapplication.viewmodels.product

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.modals.Topping
import com.example.myapplication.services.ToppingService
import com.example.myapplication.utils.Utils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ToppingViewModel :ViewModel(){
    private val _toppings = MutableLiveData<ArrayList<Topping>>()
    val toppings: LiveData<ArrayList<Topping>> = _toppings
    fun getToppings() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = Utils.getRetrofit().create(ToppingService::class.java).getAllTopping()
                _toppings.postValue(response) // để đảm bảo rằng các giá trị được cập nhật trên luồng phụ (background thread).
                println("Init app topping: $response")
            } catch (e: Exception) {
                println("Init app topping: lỗi")
            }
        }
    }
}
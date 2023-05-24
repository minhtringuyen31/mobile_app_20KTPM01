package com.example.myapplication.viewmodels.product

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.modals.Topping
import com.example.myapplication.services.ToppingService
import com.example.myapplication.utils.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit

class ToppingViewModel :ViewModel(){
    private val _toppings = MutableLiveData<ArrayList<Topping>>()
    val toppings: LiveData<ArrayList<Topping>> = _toppings
    private val retrofit: Retrofit = RetrofitClient.instance!!
    fun getToppings() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = retrofit.create(ToppingService::class.java).getAllTopping()
                _toppings.postValue(response) // để đảm bảo rằng các giá trị được cập nhật trên luồng phụ (background thread).
            } catch (e: Exception) {
                println("Init app topping: lỗi")
            }
        }
    }
}
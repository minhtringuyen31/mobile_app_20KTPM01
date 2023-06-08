package com.example.myapplication.viewmodels.promotion

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.modals.Promotion
import com.example.myapplication.services.PromotionService
import com.example.myapplication.utils.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit


class PromotionViewModel :ViewModel(){
    private val _promotions = MutableLiveData<ArrayList<Promotion>>()
    val promotions: LiveData<ArrayList<Promotion>> = _promotions
    private val _promotion = MutableLiveData<Promotion>()
    val promotion: LiveData<Promotion> = _promotion
    private val retrofit: Retrofit = RetrofitClient.instance!!
    fun getPromotions() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = retrofit.create(PromotionService::class.java).getAllPromotion()
                _promotions.postValue(response) // để đảm bảo rằng các giá trị được cập nhật trên luồng phụ (background thread).
            } catch (e: Exception) {
                // handle error
            }
        }
    }
    fun getPromotion(id:Int){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response =retrofit.create(PromotionService::class.java).getPromotion(id)
                _promotion.postValue(response) // để đảm bảo rằng các giá trị được cập nhật trên luồng phụ (background thread).
            } catch (e: Exception) {
                // handle error
            }
        }

    }
}
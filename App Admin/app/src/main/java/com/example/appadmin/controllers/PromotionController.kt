package com.example.appadmin.controllers

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appadmin.modals.Promotion
import com.example.appadmin.services.PromotionService
import com.example.appadmin.utils.Utils
import kotlinx.coroutines.launch

class PromotionController : ViewModel() {
    fun getAllPromotion(): LiveData<List<Promotion>> {
        val _promotions = MutableLiveData<List<Promotion>>()
        viewModelScope.launch {
            try {
                val response =
                    Utils.getRetrofit().create(PromotionService::class.java).getAllPromotion()
                _promotions.value = response
            } catch (e: Exception) {
                // handle error
            }
        }
        return _promotions
    }

    fun getPromotion(id: Int): LiveData<Promotion> {
        val _promotion = MutableLiveData<Promotion>()
        viewModelScope.launch {
            try {
                val response =
                    Utils.getRetrofit().create(PromotionService::class.java).getPromotionById(id)
                _promotion.value = response
            } catch (e: Exception) {
                // handle error
            }
        }
        return _promotion
    }

    fun createPromotion(promotionNew: Promotion): LiveData<Promotion> {
        val _promotion = MutableLiveData<Promotion>()
        viewModelScope.launch {
            try {
                val response =
                    Utils.getRetrofit().create(PromotionService::class.java)
                        .createPromotion(promotionNew)
                _promotion.value = response
            } catch (e: Exception) {
                // handle error
            }
        }
        return _promotion
    }

    fun updatePromotion(id: Int, promotionNew: Promotion): LiveData<Promotion> {
        val _promotion = MutableLiveData<Promotion>()
        viewModelScope.launch {
            try {
                val response = Utils.getRetrofit().create(PromotionService::class.java)
                    .updatePromotion(id, promotionNew)
                _promotion.value = response
            } catch (e: Exception) {
                // handle error
            }
        }
        return _promotion
    }

    fun deletePromotion(id: Int): LiveData<Boolean> {
        val _promotion = MutableLiveData<Boolean>()
        viewModelScope.launch {
            try {
                val response =
                    Utils.getRetrofit().create(PromotionService::class.java).deletePromotion(id)
                _promotion.value = response
            } catch (e: Exception) {
                // handle error
            }
        }
        return _promotion
    }

    fun disablePromotion(id: Int): LiveData<Boolean> {
        val _promotion = MutableLiveData<Boolean>()
        viewModelScope.launch {
            try {
                val response =
                    Utils.getRetrofit().create(PromotionService::class.java).disablePromotion(id)
                _promotion.value = response
            } catch (e: Exception) {
                // handle error
            }
        }
        return _promotion
    }

    fun enablePromotion(id: Int): LiveData<Boolean> {
        val _promotion = MutableLiveData<Boolean>()
        viewModelScope.launch {
            try {
                val response =
                    Utils.getRetrofit().create(PromotionService::class.java).enablePromotion(id)
                _promotion.value = response
            } catch (e: Exception) {
                // handle error
            }
        }
        return _promotion
    }
}
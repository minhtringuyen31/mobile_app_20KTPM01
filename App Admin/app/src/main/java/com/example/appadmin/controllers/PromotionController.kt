package com.example.appadmin.controllers

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appadmin.modals.Promotion
import com.example.appadmin.services.PromotionService
import com.example.appadmin.utils.Utils
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import okhttp3.RequestBody

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

    fun createPromotion(
        name: RequestBody,
        description: RequestBody,
        discount: RequestBody,
        start_date: RequestBody,
        end_date: RequestBody,
        quantity: RequestBody,
        code: RequestBody,
        image: MultipartBody.Part
    ): LiveData<Promotion> {
        val _promotion = MutableLiveData<Promotion>()
        viewModelScope.launch {
            try {
                val response =
                    Utils.getRetrofit().create(PromotionService::class.java).createPromotion(
                        name, description, discount, start_date, end_date, quantity, code, image
                    )
                _promotion.value = response
            } catch (e: Exception) {
                // handle error
            }
        }
        return _promotion
    }

    fun updatePromotion(
        id: Int,
        name: RequestBody,
        description: RequestBody,
        discount: RequestBody,
        start_date: RequestBody,
        end_date: RequestBody,
        quantity: RequestBody,
        code: RequestBody,
        image: MultipartBody.Part
    ): LiveData<Promotion> {
        val _promotion = MutableLiveData<Promotion>()
        viewModelScope.launch {
            try {
                val response =
                    Utils.getRetrofit().create(PromotionService::class.java).updatePromotion(
                        id,
                        name,
                        description,
                        discount,
                        start_date,
                        end_date,
                        quantity,
                        code,
                        image
                    )
                _promotion.value = response
            } catch (e: Exception) {
                // handle error
            }
        }
        return _promotion
    }

    fun updatePromotionWithoutImage(
        id: Int, promotion: Promotion
    ): LiveData<Promotion> {
        val _promotion = MutableLiveData<Promotion>()
        viewModelScope.launch {
            try {
                val response = Utils.getRetrofit().create(PromotionService::class.java)
                    .updatePromotionWithoutImage(
                        id, promotion
                    )
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
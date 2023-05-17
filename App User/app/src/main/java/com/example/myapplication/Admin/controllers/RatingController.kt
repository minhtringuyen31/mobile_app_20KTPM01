package com.example.myapplication.Admin.controllers

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.Admin.modals.Rating
import com.example.myapplication.Admin.services.RatingService
import com.example.myapplication.Admin.utils.Utils
import com.google.gson.GsonBuilder
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RatingController :ViewModel() {
    fun getAllRating() :LiveData<List<Rating>> {
        val _raing = MutableLiveData<List<Rating>>()
        viewModelScope.launch {
            try {
                val response = Utils.getRetrofit().create(RatingService::class.java).getAllRating()
                _raing.value = response
            } catch (e: Exception) {
                // handle error
            }
        }
        return _raing
    }

    fun getRating(id: Int): LiveData<Rating> {
        var _rating = MutableLiveData<Rating>()
        viewModelScope.launch {
            try {
                val response =
                    Utils.getRetrofit().create(RatingService::class.java).getRating(id)
                _rating.value = response
            } catch (e: Exception) {
                // handle error
            }
        }
        return _rating
    }

    fun createRating(rating: Rating): LiveData<Rating> {
        var _rating = MutableLiveData<Rating>()
        viewModelScope.launch {
            try {
                val response =
                    Utils.getRetrofit().create(RatingService::class.java).createRating(rating)
                _rating.value = response
            } catch (e: Exception) {
                // handle error
            }
        }
        return _rating
    }

    fun updateRating(id: Int, rating: Rating): LiveData<Rating> {
        var _rating = MutableLiveData<Rating>()
        viewModelScope.launch {
            try {
                val response =
                    Utils.getRetrofit().create(RatingService::class.java).updateRating(id, rating)
                _rating.value = response
            } catch (e: Exception) {
                // handle error
            }
        }
        return _rating
    }

    fun deleteRating(id: Int): LiveData<Boolean> {
        var _rating = MutableLiveData<Boolean>()
        viewModelScope.launch {
            try {
                val response =
                    Utils.getRetrofit().create(RatingService::class.java).deleteRating(id)
                _rating.value = response
            } catch (e: Exception) {
                // handle error
            }
        }
        return _rating
    }

    fun disableRating(id: Int): LiveData<Boolean> {
        var _rating = MutableLiveData<Boolean>()
        viewModelScope.launch {
            try {
                val response =
                    Utils.getRetrofit().create(RatingService::class.java).disableRating(id)
                _rating.value = response
            } catch (e: Exception) {
                // handle error
            }
        }
        return _rating
    }

    fun enableRating(id: Int): LiveData<Boolean> {
        var _rating = MutableLiveData<Boolean>()
        viewModelScope.launch {
            try {
                val response =
                    Utils.getRetrofit().create(RatingService::class.java).enableRating(id)
                _rating.value = response
            } catch (e: Exception) {
                // handle error
            }
        }
        return _rating
    }
}
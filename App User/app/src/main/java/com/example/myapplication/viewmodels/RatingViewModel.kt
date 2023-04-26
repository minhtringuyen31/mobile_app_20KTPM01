package com.example.myapplication.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.modals.Rating
import androidx.lifecycle.viewModelScope
import com.example.myapplication.services.RatingService
import com.example.myapplication.utils.Utils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RatingViewModel : ViewModel(){
    private val _ratings = MutableLiveData<ArrayList<Rating>>()
    val ratings: LiveData<ArrayList<Rating>> = _ratings

    fun getRating(id: Int){
        viewModelScope.launch(Dispatchers.IO){
            try{
                val response = Utils.getRetrofit().create(RatingService::class.java).getRating(id)
                _ratings.postValue(response)
            } catch (e:Exception){
                println("Views: $e")
            }
        }
    }
}
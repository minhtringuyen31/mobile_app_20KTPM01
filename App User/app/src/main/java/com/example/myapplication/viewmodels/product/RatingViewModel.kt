package com.example.myapplication.viewmodels.product

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.modals.Rating
import com.example.myapplication.services.RatingService
import com.example.myapplication.utils.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit

class RatingViewModel : ViewModel(){
    private val _ratings = MutableLiveData<ArrayList<Rating>>()
    val ratings: LiveData<ArrayList<Rating>> = _ratings
    private val retrofit: Retrofit = RetrofitClient.instance!!

    fun getRating(productId: Int){
        viewModelScope.launch(Dispatchers.IO){
            try{
                val response = retrofit.create(RatingService::class.java).getRating(productId)
                _ratings.postValue(response)

            } catch (e:Exception){
                println("Views2: $e")
            }
        }
    }

    fun postRating(rating: Rating){
        viewModelScope.launch(Dispatchers.IO){
            try{

                val response = retrofit.create(RatingService::class.java).postRating(rating)

            } catch (e:Exception) {
                println("Views: $e")
            }
        }
    }

    companion object {
        fun getInstance(): RatingViewModel {
            return RatingViewModel()
        }
    }
}
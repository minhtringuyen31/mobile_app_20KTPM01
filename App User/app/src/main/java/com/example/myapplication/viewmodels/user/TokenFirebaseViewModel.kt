package com.example.myapplication.viewmodels.user

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.modals.TokenFireBaseRequest
import com.example.myapplication.services.TokenFirebase
import com.example.myapplication.utils.RetrofitClient
import kotlinx.coroutines.launch
import retrofit2.Retrofit

class TokenFirebaseViewModel:ViewModel() {
    private val _newToken = MutableLiveData<TokenFireBaseRequest>() // theo doi bien minh chi dinh , muon render UI
    val newToken: LiveData<TokenFireBaseRequest> = _newToken // du lieu truc t
    private val retrofit: Retrofit = RetrofitClient.instance!!
    fun createToken(value:TokenFireBaseRequest) {
        viewModelScope.launch {
            try {
                val response = retrofit.create(TokenFirebase::class.java).createTokenFB(value);
                _newToken.postValue(response)

            } catch (e: Exception) {
            }
        }
    }
}
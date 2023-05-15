package com.example.myapplication.viewmodels.user

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.modals.TokenFireBaseRequest
import com.example.myapplication.modals.User
import com.example.myapplication.services.TokenFirebase
import com.example.myapplication.services.UserService
import com.example.myapplication.utils.Utils
import kotlinx.coroutines.launch

class TokenFirebaseViewModel:ViewModel() {
    private val _newToken = MutableLiveData<TokenFireBaseRequest>() // theo doi bien minh chi dinh , muon render UI
    val newToken: LiveData<TokenFireBaseRequest> = _newToken // du lieu truc t

    fun createToken(value:TokenFireBaseRequest) {
        viewModelScope.launch {
            try {
                val response = Utils.getRetrofit().create(TokenFirebase::class.java).createTokenFB(value);
                _newToken.postValue(response)
                 println(response)
            } catch (e: Exception) {
            }
        }
    }
}
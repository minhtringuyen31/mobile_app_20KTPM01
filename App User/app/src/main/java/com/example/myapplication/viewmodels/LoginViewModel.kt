package com.example.myapplication.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.modals.Category
import com.example.myapplication.modals.LoginRequest
import com.example.myapplication.modals.User
import com.example.myapplication.services.AuthenService
import com.example.myapplication.utils.Resource
import com.example.myapplication.utils.Utils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.Dispatcher
import okhttp3.internal.Util

class LoginViewModel:ViewModel() {
    private var _loginResult: MutableLiveData<Resource<LoginRequest>> = MutableLiveData()
    val statusLogin: LiveData<Resource<LoginRequest>> = _loginResult
    fun loginUser(request: LoginRequest) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                 val response = Utils.getRetrofit().create(AuthenService::class.java).loginUser(request);
                Resource.loading(data = null)
                if(response.getStatusUser()==1){
                    _loginResult.postValue( Resource.success(data=response))

                }
                else
                {

                    _loginResult.postValue( Resource.error(data=null, message = "No Found!"))
                }

            } catch (e: Exception) {
                _loginResult.postValue( Resource.error(data = null, message ="Error Occurred!"))
            }
        }
    }

}
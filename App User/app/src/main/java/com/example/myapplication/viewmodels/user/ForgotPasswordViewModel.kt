package com.example.myapplication.viewmodels.user

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.modals.ForgotPassRequest
import com.example.myapplication.services.UserService
import com.example.myapplication.utils.Resource
import com.example.myapplication.utils.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit

class ForgotPasswordViewModel:ViewModel() {

    private var _setotp: MutableLiveData<Resource<ForgotPassRequest>> = MutableLiveData()
    val setotp: LiveData<Resource<ForgotPassRequest>> = _setotp

    private var _checkotp: MutableLiveData<Resource<ForgotPassRequest>> = MutableLiveData()
    val checkotp: LiveData<Resource<ForgotPassRequest>> = _checkotp
    private val retrofit: Retrofit = RetrofitClient.instance!!
    fun SetOTP(request:ForgotPassRequest){
        viewModelScope.launch(Dispatchers.IO) {
            try {

                val response = retrofit.create(UserService::class.java).SetOTP(request)
                Resource.loading(data = null)
                println("test"+response)

                if(response.getEmail()!=null){
                    println("test"+response)
                    _setotp.postValue( Resource.success(data=response))
                }
                else
                {
                    _setotp.postValue( Resource.error(data=null, message = "No Found Email!"))
                }
            }
            catch (e:java.lang.Exception) {
                _setotp.postValue( Resource.error(data = null, message ="Error Occurred!"))

            }
        }
    }

    fun CheckOTP(request:ForgotPassRequest){
        viewModelScope.launch(Dispatchers.IO) {
            try {

                println(request)
                val response = retrofit.create(UserService::class.java).CheckOTP(request)
                Resource.loading(data = null)
                println("test"+response)

                if(response.getOTP()!=null){
                    println("test"+response)
                    _checkotp.postValue( Resource.success(data=response))
                }
                else
                {
                    _checkotp.postValue( Resource.error(data=null, message = "No Found Email!"))
                }


            }
            catch (e:java.lang.Exception) {
            _checkotp.postValue( Resource.error(data = null, message ="Error Occurred!"))
            }
        }
    }

}
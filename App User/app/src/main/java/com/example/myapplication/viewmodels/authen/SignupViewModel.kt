package com.example.myapplication.viewmodels.authen

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.modals.SignupRequest
import com.example.myapplication.services.UserService
import com.example.myapplication.utils.Resource
import com.example.myapplication.utils.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit

class SignupViewModel: ViewModel() {
    private var _signup: MutableLiveData<Resource<SignupRequest>> = MutableLiveData()
    val signup: LiveData<Resource<SignupRequest>> = _signup
    private val retrofit: Retrofit = RetrofitClient.instance!!

    @SuppressLint("SuspiciousIndentation")
    fun SignUp(request: SignupRequest) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                println(request)
                val response = retrofit.create(UserService::class.java).SignUp(request)
                Resource.loading(data = null)

                if(response.getEmail()!=null){
                    _signup.postValue( Resource.success(data=response))
                }
                else
                {
                    _signup.postValue( Resource.error(data=null, message = "No Found!"))
                }

            }
            catch (e:java.lang.Exception){
                _signup.postValue( Resource.error(data = null, message ="Error Occurred!"))
            }
        }
    }

}
package com.example.myapplication.viewmodels.authen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.modals.LoginRequest
import com.example.myapplication.services.AuthenService
import com.example.myapplication.utils.Resource
import com.example.myapplication.utils.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit


class LoginViewModel():ViewModel() {
    private var _loginResult: MutableLiveData<Resource<LoginRequest>> = MutableLiveData()
    val statusLogin: LiveData<Resource<LoginRequest>> = _loginResult
    private val retrofit: Retrofit = RetrofitClient.instance!!

//application: Application
    fun loginUser(request: LoginRequest) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = retrofit.create(AuthenService::class.java).loginUser(request);
                println("Trả về "+response)
                Resource.loading(data = null)
                if(response.getStatusUser()==1){
                    _loginResult.postValue( Resource.success(data=response))
                }
//                else if(response.getStatusUser()==-1){
//                    _loginResult.postValue( Resource.error(data=null, message = "Gmail chưa đăng kí tài khoản"))
//                }
                else if(response.getStatusUser()==-2){
                    _loginResult.postValue( Resource.error(data=null, message = "Sai mật khẩu hoặc tên đăng nhập"))
                }
                else
                {
                    _loginResult.postValue( Resource.error(data=null, message = "không tìm thấy tài khoản"))
                }

            } catch (e: Exception) {
                _loginResult.postValue( Resource.error(data = null, message ="Error Occurred!"))
            }
        }
    }

    fun signIn(idToken:String){
        viewModelScope.launch(Dispatchers.Main) {
            try {
                val response =retrofit.create(AuthenService::class.java).signIn(idToken)
                Resource.loading(data = null)
                if(response.getStatusUser()==1){
                    _loginResult.postValue( Resource.success(data=response))
                }
                else
                {
                    _loginResult.postValue( Resource.error(data=null, message = "Không tìm thấy tài khoản"))
                }



            } catch (e: Exception) {
                _loginResult.postValue( Resource.error(data = null, message ="Error Occurred!"))
            }
        }
    }

}

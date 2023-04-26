package com.example.myapplication.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.modals.ChangePassRequest
import com.example.myapplication.modals.SignupRequest
import com.example.myapplication.services.UserService
import com.example.myapplication.utils.Resource
import com.example.myapplication.utils.Utils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ChangePassViewModel:ViewModel() {
    private var _changePass: MutableLiveData<Resource<ChangePassRequest>> = MutableLiveData()
    val changePass: LiveData<Resource<ChangePassRequest>> = _changePass
    fun ChangePass(id:Int, request:ChangePassRequest){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                println(request)
                val response = Utils.getRetrofit().create(UserService::class.java).ChangePass(id,request)
                Resource.loading(data = null)
                println("test"+response)

                if(response.getNewPass()!=null){
                    println("test"+response)
                    _changePass.postValue( Resource.success(data=response))
                }
                else
                {
                    _changePass.postValue( Resource.error(data=null, message = "No Found!"))
                }

            }
            catch (e:java.lang.Exception){
                _changePass.postValue( Resource.error(data = null, message ="Error Occurred!"))
            }
        }
    }
}
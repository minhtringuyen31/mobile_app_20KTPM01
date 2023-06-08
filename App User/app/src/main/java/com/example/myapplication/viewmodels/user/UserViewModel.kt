package com.example.myapplication.viewmodels.user

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.modals.User
import com.example.myapplication.services.UserService
import com.example.myapplication.utils.RetrofitClient
import kotlinx.coroutines.launch
import retrofit2.Retrofit


class UserViewModel:ViewModel() {
    private val _user = MutableLiveData<User>() // theo doi bien minh chi dinh , muon render UI
    val user: LiveData<User> = _user // du lieu truc tiep muon render ra ngoai
    private val retrofit: Retrofit = RetrofitClient.instance!!
    fun getUser(id:Int) {
        viewModelScope.launch {
            try {
                val response =retrofit.create(UserService::class.java).getUser(id);

                _user.value=response
            } catch (e: Exception) {
                // handle error
            }
        }
    }
//    @SuppressLint("SuspiciousIndentation")
    fun getUserbyEmail(email:String) {
//        viewModelScope.launch(Dispatchers.IO){
//            try {
//                //println(request)
//                val response =  Utils.getRetrofit().create(UserService::class.java).getUserByEmail(email);
//                Resource.loading(data = null)
//                //println("test"+response)
//
//                if(response.getEmail()!=null){
//                    //println("test"+response)
//                    _user.postValue( Resource.success(data=response))
//                }
//                else
//                {
//                    _user.postValue( Resource.error(data=null, message = "No Found!"))
//                }
//
//            }
//            catch (e:java.lang.Exception){
//                _user.postValue( Resource.error(data = null, message ="Error Occurred!"))
//            }
//        }
        /////////////////////////////////////////////////------
        viewModelScope.launch {
            try {
                val response = retrofit.create(UserService::class.java).getUserByEmail(email);
                println("View: $response")
                _user.value=response
            } catch (e: Exception) {
                // handle error
            }
        }
    }

}
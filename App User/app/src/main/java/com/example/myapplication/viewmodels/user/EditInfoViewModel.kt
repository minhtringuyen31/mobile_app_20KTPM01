package com.example.myapplication.viewmodels.user

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.modals.EditInfoRequest
import com.example.myapplication.services.UserService
import com.example.myapplication.utils.Resource
import com.example.myapplication.utils.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit

class EditInfoViewModel:ViewModel() {
    private var _editInfo: MutableLiveData<Resource<EditInfoRequest>> = MutableLiveData()
    val editInfo: LiveData<Resource<EditInfoRequest>> = _editInfo
    private val retrofit: Retrofit = RetrofitClient.instance!!
    fun EditInfo(id:Int, request: EditInfoRequest){
        viewModelScope.launch(Dispatchers.IO) {
            try {

                val response = retrofit.create(UserService::class.java).EditInfo(id,request)
                Resource.loading(data = null)


                if(response.getName()!=null){

                    _editInfo.postValue( Resource.success(data=response))
                }
                else
                {
                    _editInfo.postValue( Resource.error(data=null, message = "No Found!"))
                }

            }
            catch (e:java.lang.Exception){
                _editInfo.postValue( Resource.error(data = null, message ="Error Occurred!"))
            }
        }
    }

}
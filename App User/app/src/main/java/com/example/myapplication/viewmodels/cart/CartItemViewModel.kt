package com.example.myapplication.viewmodels.cart

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.modals.CartItem
import com.example.myapplication.services.CartItemService
import com.example.myapplication.utils.Utils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CartItemViewModel:ViewModel() {
    private val _cartItems = MutableLiveData<ArrayList<CartItem>>()
    val cartItems: LiveData<ArrayList<CartItem>> = _cartItems

    private val _status = MutableLiveData<Int>()
     val status: LiveData<Int> = _status

    private val _newCartItem = MutableLiveData<CartItem>()
    val newCategory: LiveData<CartItem> = _newCartItem

//    fun getAllItemsCart() {
//        // App 2 thread MainThread(UI Thread) và Background Thread --> ANR
//        viewModelScope.launch(Dispatchers.IO) {
//            try {
//                val response = Utils.getRetrofit().create(CartItemService::class.java).getAllCartItem()
//                _cartItems.postValue(response) // để đảm bảo rằng các giá trị được cập nhật trên luồng phụ (background thread).
//                println(response)
//            } catch (e: Exception) {
//                // handle error
//                println("loi")
//            }
//        }
//    }

    fun getItemsCart(userId: Int){
        // App 2 thread MainThread(UI Thread) và Background Thread --> ANR
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = Utils.getRetrofit().create(CartItemService::class.java).getCartItem(userId)
                _cartItems.postValue(response) // để đảm bảo rằng các giá trị được cập nhật trên luồng phụ (background thread).
                println(response)
            } catch (e: Exception) {
                // handle error
                println("loi")
            }
        }
    }
    fun deleteCartItem(id:Int) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = Utils.getRetrofit().create(CartItemService::class.java).deleteCartItem(id)
                _status.postValue(response)
            } catch (e: Exception) {
                // handle error
            }
        }
    }

    fun createCartItem(cartItem:CartItem) {
        viewModelScope.launch {
            try {
                val response = Utils.getRetrofit().create(CartItemService::class.java).createCartItem(cartItem)
                println("View: $response")
                _newCartItem.postValue(response)
            } catch (e: Exception) {
                // handle error
            }
        }
    }


}
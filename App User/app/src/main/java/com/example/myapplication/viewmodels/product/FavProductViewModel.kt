package com.example.myapplication.viewmodels.product

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.modals.FavProductItem
import com.example.myapplication.modals.Product
import com.example.myapplication.services.ProductService
import com.example.myapplication.utils.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit


class FavProductViewModel: ViewModel() {
    private val _products = MutableLiveData<ArrayList<Product>>()
    val products: LiveData<ArrayList<Product>> = _products
    private val _product = MutableLiveData<Product>()
    val product: LiveData<Product> = _product

    private val _status = MutableLiveData<Boolean>()
    val status: LiveData<Boolean> = _status

    private val _check = MutableLiveData<Boolean>()
    val check: LiveData<Boolean> = _check
    private val retrofit: Retrofit = RetrofitClient.instance!!
    fun getAllFavProducts(userId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response =
                    retrofit.create(ProductService::class.java).getAllFavProduct(userId)
                _products.postValue(response)

            } catch (e: Exception) {
                println("View : $e")
            }
        }
    }

    fun addFavProduct(favProduct : FavProductItem){
        viewModelScope.launch(Dispatchers.Main) {
            try {
                val response =
                    retrofit.create(ProductService::class.java).addFavProduct(favProduct)

                _product.postValue(response)
            } catch (e: Exception) {
                println("View : $e")
            }
        }
    }

    fun removeFavProduct(favProduct: FavProductItem){
        viewModelScope.launch(Dispatchers.Main) {
            try {
                val response =
                    retrofit.create(ProductService::class.java).removeFavProduct(favProduct)
                _status.postValue(response)
            } catch (e: Exception) {
                println("View : $e")
            }
        }
    }

    fun isExistedFacProduct(userId: Int, productId: Int) {
        viewModelScope.launch(Dispatchers.Main) {
            try {
                val response =
                    retrofit.create(ProductService::class.java).findAFavProduct(userId, productId)
                _check.postValue(response)
            } catch (e: Exception) {
                println("View : $e")
            }
        }
    }


}
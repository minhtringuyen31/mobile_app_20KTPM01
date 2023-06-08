package com.example.myapplication.viewmodels.product

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.modals.Product
import com.example.myapplication.services.ProductService
import com.example.myapplication.utils.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit

class ProductViewModel: ViewModel(){

    private val _products = MutableLiveData<ArrayList<Product>>()
    val products: LiveData<ArrayList<Product>> = _products
    private val _productsSales = MutableLiveData<ArrayList<Product>>()
    val productsSales: LiveData<ArrayList<Product>> = _productsSales
    private val _product = MutableLiveData<Product>()
    val product: LiveData<Product> = _product
    private val retrofit: Retrofit = RetrofitClient.instance!!
    private val _rating = MutableLiveData<Float>()
    val rating: LiveData<Float> = _rating

    fun getRating(productId: Int) {
        viewModelScope.launch(Dispatchers.Main) {
            try {
                val response = retrofit.create(ProductService::class.java).getRating(productId)
                _rating.postValue(response)
            } catch (e: Exception) {
                println("View:  $e")
            }
        }
    }

    fun getProducts() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = retrofit.create(ProductService::class.java).getAllProduct()
//                val response=Utils.getRetrofit().create(ProductService::class.java).getAllProduct()
                _products.postValue(response)
            } catch (e: Exception) {
                println("View: $e")
            }
        }
    }
    fun getProductsSales() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = retrofit.create(ProductService::class.java).getAllProductSale()
                _productsSales.postValue(response)
            } catch (e: Exception) {
                println("View: $e")
            }
        }
    }

    fun getProduct(id:Int) {
        viewModelScope.launch {
            try {
                val response =retrofit.create(ProductService::class.java).getProduct(id);
                _product.value = response

            } catch (e: Exception) {
                // handle error

            }
        }
    }
    fun getProductByCategory(categoryId : Int){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = retrofit.create(ProductService::class.java)
                    .getProductByCategory(categoryId)
                _products.postValue(response)
            } catch (e: Exception){
                println("View: $e")
            }
        }
    }
}
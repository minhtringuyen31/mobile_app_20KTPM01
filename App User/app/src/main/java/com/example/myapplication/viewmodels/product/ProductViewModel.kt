package com.example.myapplication.viewmodels.product

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.modals.Product
import com.example.myapplication.services.ProductService
import com.example.myapplication.utils.Utils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProductViewModel: ViewModel(){

    private val _products = MutableLiveData<ArrayList<Product>>()
    val products: LiveData<ArrayList<Product>> = _products
    private val _productsSales = MutableLiveData<ArrayList<Product>>()
    val productsSales: LiveData<ArrayList<Product>> = _productsSales
    private val _product = MutableLiveData<Product>()
    val product: LiveData<Product> = _product

    private val _rating = MutableLiveData<Float>()
    val rating: LiveData<Float> = _rating
//
//    private val _status = MutableLiveData<Int>()
//    val status: LiveData<Int> = _status
//
//    private val _newproduct = MutableLiveData<Product>()
//    val newproduct: LiveData<Product> = _newproduct
//
//    private val _updateproduct = MutableLiveData<Product>()
//    val updateproduct: LiveData<Product> = _updateproduct

    fun getRating(productId: Int) {
        viewModelScope.launch(Dispatchers.Main) {
            try {
                val response = Utils.getRetrofit().create(ProductService::class.java).getRating(productId)
                _rating.postValue(response)
            } catch (e: Exception) {
                println("View:  $e")
            }
        }
    }

    fun getProducts() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = Utils.getRetrofit().create(ProductService::class.java).getAllProduct()
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
                val response = Utils.getRetrofit().create(ProductService::class.java).getAllProductSale()
//                val response=Utils.getRetrofit().create(ProductService::class.java).getAllProduct()
                _productsSales.postValue(response)
                println("Sale"+response)
            } catch (e: Exception) {
                println("View: $e")
            }
        }
    }

    fun getProduct(id:Int) {
        viewModelScope.launch {
            try {
                val response = Utils.getRetrofit().create(ProductService::class.java).getProduct(id);
                _product.value = response
                println("Product: $response")
            } catch (e: Exception) {
                // handle error

            }
        }
    }
    fun getProductByCategory(categoryId : Int){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = Utils.getRetrofit().create(ProductService::class.java)
                    .getProductByCategory(categoryId)
                _products.postValue(response)
            } catch (e: Exception){
                println("View: $e")
            }
        }
    }
//    fun deleteProduct(id:Int) {
//        viewModelScope.launch {
//            try {
//                val response = Utils.getRetrofit().create(ProductService::class.java).deleteProduct(id);
//                _status.value=response;
//            } catch (e: Exception) {
//                // handle error
//            }
//        }
//    }
//    fun updateProduct(id:Int){
//
//        viewModelScope.launch {
//            try {
//                val response = Utils.getRetrofit().create(ProductService::class.java).updateProduct(id,
//                    Product(7,"Phildi Chôc","Đồ uống",56.0,"M",12.0,12.0,12.0,"Blabla","images/product/product16.png",true,"123","12/02/2002","12.",1,0)
//                );
//
//                _updateproduct.value=response;
//
//
//            } catch (e: Exception) {
//                // handle error
//            }
//        }
//
//    }
//    fun createproduct() {
//        viewModelScope.launch {
//            try {
//                val newproduct= Product(6,"Phildi Chôc","Đồ uống",56.0,"M",12.0,12.0,12.0,"Blabla","images/product/product16.png",true,"123","12/02/2002","12.",1,0)
//                val response = Utils.getRetrofit().create(ProductService::class.java).createProduct(newproduct);
//                println("View: $response")
//                _newproduct.value=response;
//            } catch (e: Exception) {
//                // handle error
//            }
//        }
//    }
}
package com.example.appadmin.controllers

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appadmin.modals.Product
import com.example.appadmin.services.ProductService
import com.example.appadmin.utils.Utils
import kotlinx.coroutines.launch

class ProductController : ViewModel() {
    fun getProduct(id: Int): LiveData<Product> {
        val _product = MutableLiveData<Product>()
        viewModelScope.launch {
            try {
                val response = Utils.getRetrofit().create(ProductService::class.java).getProduct(id)
                _product.value = response
            } catch (e: Exception) {
                // handle error
            }
        }
        return _product
    }

    fun getAllProduct(): LiveData<List<Product>> {
        val _products = MutableLiveData<List<Product>>()
        viewModelScope.launch {
            try {
                val response =
                    Utils.getRetrofit().create(ProductService::class.java).getAllProduct()
                _products.value = response
            } catch (e: Exception) {
                // handle error
            }
        }
        return _products
    }

    fun createProduct(productNew: Product): LiveData<Product> {
        val _product = MutableLiveData<Product>()
        viewModelScope.launch {
            try {
                val response =
                    Utils.getRetrofit().create(ProductService::class.java).createProduct(productNew)
                _product.value = response
            } catch (e: Exception) {
                // handle error
            }
        }
        return _product
    }

    fun updateProduct(id: Int, productNew: Product): LiveData<Product> {
        val _product = MutableLiveData<Product>()
        viewModelScope.launch {
            try {
                val response = Utils.getRetrofit().create(ProductService::class.java)
                    .updateProduct(id, productNew)
                _product.value = response
            } catch (e: Exception) {
                // handle error
            }
        }
        return _product
    }

    fun deleteProduct(id: Int): LiveData<Boolean> {
        val _product = MutableLiveData<Boolean>()
        viewModelScope.launch {
            try {
                val response =
                    Utils.getRetrofit().create(ProductService::class.java).deleteProduct(id)
                _product.value = response
            } catch (e: Exception) {
                // handle error
            }
        }
        return _product
    }

    fun disableProduct(id: Int): LiveData<Boolean> {
        val _product = MutableLiveData<Boolean>()
        viewModelScope.launch {
            try {
                val response =
                    Utils.getRetrofit().create(ProductService::class.java).disableProduct(id)
                _product.value = response
            } catch (e: Exception) {
                // handle error
            }
        }
        return _product
    }

    fun enableProduct(id: Int): LiveData<Boolean> {
        val _product = MutableLiveData<Boolean>()
        viewModelScope.launch {
            try {
                val response =
                    Utils.getRetrofit().create(ProductService::class.java).enableProduct(id)
                _product.value = response
            } catch (e: Exception) {
                // handle error
            }
        }
        return _product
    }

    fun availableProduct(id: Int): LiveData<Boolean> {
        val _product = MutableLiveData<Boolean>()
        viewModelScope.launch {
            try {
                val response =
                    Utils.getRetrofit().create(ProductService::class.java).availableProduct(id)
                _product.value = response
            } catch (e: Exception) {
                // handle error
            }
        }
        return _product
    }

    fun unavailableProduct(id: Int): LiveData<Boolean> {
        val _product = MutableLiveData<Boolean>()
        viewModelScope.launch {
            try {
                val response =
                    Utils.getRetrofit().create(ProductService::class.java).unavailableProduct(id)
                _product.value = response
            } catch (e: Exception) {
                // handle error
            }
        }
        return _product
    }
}
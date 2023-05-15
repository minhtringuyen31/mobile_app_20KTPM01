package com.example.appadmin.controllers

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appadmin.modals.Product
import com.example.appadmin.services.ProductService
import com.example.appadmin.utils.Utils
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import okhttp3.RequestBody

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
                println(response[0].getId())
            } catch (e: Exception) {
                // handle error
            }
        }
        return _products
    }

    fun createProduct(
        name: RequestBody,
        description: RequestBody,
        size: RequestBody,
        price_S: RequestBody,
        price_M: RequestBody,
        price_L: RequestBody,
        status: RequestBody,
        category_id: RequestBody,
        update_date: RequestBody,
        release_date: RequestBody,
        sales: RequestBody,
        image: MultipartBody.Part
    ): LiveData<Product> {
        val _product = MutableLiveData<Product>()
        viewModelScope.launch {
            try {
                val response =
                    Utils.getRetrofit().create(ProductService::class.java).createProduct(
                        name,
                        description,
                        size,
                        price_S,
                        price_M,
                        price_L,
                        status,
                        category_id,
                        update_date,
                        release_date,
                        sales,
                        image
                    )
                _product.value = response
            } catch (e: Exception) {
                // handle error
            }
        }
        return _product
    }

    fun updateProduct(
        id: Int,
        name: RequestBody,
        description: RequestBody,
        size: RequestBody,
        price_S: RequestBody,
        price_M: RequestBody,
        price_L: RequestBody,
        category_id: RequestBody,
        update_date: RequestBody,
        image: MultipartBody.Part
    ): LiveData<Product> {
        val _product = MutableLiveData<Product>()
        viewModelScope.launch {
            try {
                val response = Utils.getRetrofit().create(ProductService::class.java)
                    .updateProduct(
                        id,
                        name,
                        description,
                        size,
                        price_S,
                        price_M,
                        price_L,
                        category_id,
                        update_date,
                        image
                    )
                _product.value = response
            } catch (e: Exception) {
                // handle error
            }
        }
        return _product
    }

    fun updateWithoutImage(id: Int, product: Product): LiveData<Product> {
        val _product = MutableLiveData<Product>()
        viewModelScope.launch {
            try {
                val response = Utils.getRetrofit().create(ProductService::class.java)
                    .updateWithoutImage(id, product)
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
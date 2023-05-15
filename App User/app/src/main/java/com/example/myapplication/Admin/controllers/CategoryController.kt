package com.example.myapplication.Admin.controllers

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.Admin.modals.Category
import com.example.myapplication.Admin.services.CategoryService
import com.example.myapplication.Admin.utils.Utils

import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import okhttp3.RequestBody

class CategoryController : ViewModel() {
    fun getCategory(id: Int): LiveData<Category> {
        var _category = MutableLiveData<Category>()
        viewModelScope.launch {
            try {
                val response =
                    Utils.getRetrofit().create(CategoryService::class.java).getCategory(id)
                _category.value = response
            } catch (e: Exception) {
                // handle error
            }
        }
        return _category
    }

    fun getAllCategory(): LiveData<List<Category>> {
        var _categories = MutableLiveData<List<Category>>()
        viewModelScope.launch {
            try {
                val response =
                    Utils.getRetrofit().create(CategoryService::class.java).getAllCategory()
                _categories.value = response
            } catch (e: Exception) {
                // handle error
            }
        }
        return _categories
    }

    fun createCategory(
        name: RequestBody,
        image: MultipartBody.Part
    ): LiveData<Category> {
        var _category = MutableLiveData<Category>()
        viewModelScope.launch {
            try {
                val response =
                    Utils.getRetrofit().create(CategoryService::class.java).createCategory(
                        name,
                        image
                    )
                _category.value = response
            } catch (e: Exception) {
                // handle error
            }
        }
        return _category
    }

    fun updateCategory(
        id: Int,
        name: RequestBody,
        image: MultipartBody.Part
    ): LiveData<Category> {
        var _category = MutableLiveData<Category>()
        viewModelScope.launch {
            try {
                val response = Utils.getRetrofit().create(CategoryService::class.java)
                    .updateCategory(id, name, image)
                _category.value = response
            } catch (e: Exception) {
                // handle error
            }
        }
        return _category
    }

    fun updateCategoryWithoutImage(
        id: Int,
        category: Category
    ): LiveData<Category> {
        var _category = MutableLiveData<Category>()
        viewModelScope.launch {
            try {
                val response = Utils.getRetrofit().create(CategoryService::class.java)
                    .updateCategoryWithoutImage(id, category)
                _category.value = response
            } catch (e: Exception) {
                // handle error
            }
        }
        return _category
    }

    fun deleteCategory(id: Int): LiveData<Boolean> {
        var _isDeleted = MutableLiveData<Boolean>()
        viewModelScope.launch {
            try {
                val response =
                    Utils.getRetrofit().create(CategoryService::class.java).deleteCategory(id)
                _isDeleted.value = response
            } catch (e: Exception) {
                // handle error
            }
        }
        return _isDeleted
    }

    fun disableCategory(id: Int): LiveData<Boolean> {
        var _isDisabled = MutableLiveData<Boolean>()
        viewModelScope.launch {
            try {
                val response =
                    Utils.getRetrofit().create(CategoryService::class.java).disableCategory(id)
                _isDisabled.value = response
            } catch (e: Exception) {
                // handle error
            }
        }
        return _isDisabled
    }

    fun enableCategory(id: Int): LiveData<Boolean> {
        var _isEnabled = MutableLiveData<Boolean>()
        viewModelScope.launch {
            try {
                val response =
                    Utils.getRetrofit().create(CategoryService::class.java).enableCategory(id)
                _isEnabled.value = response
            } catch (e: Exception) {
                // handle error
            }
        }
        return _isEnabled
    }
}
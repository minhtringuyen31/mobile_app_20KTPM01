package com.example.appadmin.controllers

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appadmin.modals.Category
import com.example.appadmin.services.CategoryService
import com.example.appadmin.utils.Utils
import kotlinx.coroutines.launch

class CategoryController: ViewModel() {
    fun getCategory(id: Int): LiveData<Category> {
        var _category = MutableLiveData<Category>()
        viewModelScope.launch {
            try {
                val response = Utils.getRetrofit().create(CategoryService::class.java).getCategory(id)
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
                val response = Utils.getRetrofit().create(CategoryService::class.java).getAllCategory()
                _categories.value = response
            } catch (e: Exception) {
                // handle error
            }
        }
        return _categories
    }

    fun createCategory(categoryNew: Category): LiveData<Category> {
        var _category = MutableLiveData<Category>()
        viewModelScope.launch {
            try {
                val response = Utils.getRetrofit().create(CategoryService::class.java).createCategory(categoryNew)
                _category.value = response
            } catch (e: Exception) {
                // handle error
            }
        }
        return _category
    }

    fun updateCategory(id: Int, categoryNew: Category): LiveData<Category> {
        var _category = MutableLiveData<Category>()
        viewModelScope.launch {
            try {
                val response = Utils.getRetrofit().create(CategoryService::class.java).updateCategory(id, categoryNew)
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
                val response = Utils.getRetrofit().create(CategoryService::class.java).deleteCategory(id)
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
                val response = Utils.getRetrofit().create(CategoryService::class.java).disableCategory(id)
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
                val response = Utils.getRetrofit().create(CategoryService::class.java).enableCategory(id)
                _isEnabled.value = response
            } catch (e: Exception) {
                // handle error
            }
        }
        return _isEnabled
    }
}
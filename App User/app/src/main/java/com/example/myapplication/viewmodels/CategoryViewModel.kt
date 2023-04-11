package com.example.myapplication.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.modals.Category
import com.example.myapplication.services.CategoryService
import com.example.myapplication.utils.Utils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CategoryViewModel :ViewModel(){
    private val _categories = MutableLiveData<ArrayList<Category>>()
    val categories: LiveData<ArrayList<Category>> = _categories
    private val _category = MutableLiveData<Category>()
    val category: LiveData<Category> = _category

    private val _status = MutableLiveData<Int>()
    private val status: LiveData<Int> = _status

    private val _newcategory = MutableLiveData<Category>()
    val newCategory: LiveData<Category> = _newcategory

    private val _updatecategory = MutableLiveData<Category>()
    val updateCategory: LiveData<Category> = _updatecategory


    fun getCategories() {
        // App 2 thread; MainThread(UI Thread) và Background Thread --> ANR
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = Utils.getRetrofit().create(CategoryService::class.java).getAllCategory()
                _categories.postValue(response) // để đảm bảo rằng các giá trị được cập nhật trên luồng phụ (background thread).
            } catch (e: Exception) {
                // handle error
            }
        }
    }

    fun getCategory(id:Int) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = Utils.getRetrofit().create(CategoryService::class.java).getCategory(id);
                println("Init app catgory: $response")
                _category.postValue(response)
            } catch (e: Exception) {
                // handle error
            }
        }
    }
    fun deleteCategory(id:Int) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = Utils.getRetrofit().create(CategoryService::class.java).deleteCategory(id);
                _status.postValue(response);
            } catch (e: Exception) {
                // handle error
            }
        }
    }
    fun updateCategory(id:Int){

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = Utils.getRetrofit().create(CategoryService::class.java).updateCategory(id,
                    Category(1,"Cà phê truyền thống","images/categories/Caphett.png",0)
                );

                _updatecategory.postValue(response);


            } catch (e: Exception) {
                // handle error
            }
        }

    }
    fun createCategory() {
        viewModelScope.launch {
            try {
                val newCategory=Category(0,"Cà phê truyền thống","images/categories/Caphett.png",0)
                val response = Utils.getRetrofit().create(CategoryService::class.java).createCategory(newCategory);
                println("View: $response")
                _newcategory.postValue(response);
            } catch (e: Exception) {
                // handle error
            }
        }
    }
}


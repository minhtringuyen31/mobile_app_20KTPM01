package com.example.myapplication.controllers

import com.example.myapplication.modals.Category
import com.example.myapplication.services.CategoryService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CategoryController {
    private lateinit var  categories:List<Category>
    private lateinit var categoryService: CategoryService
//    fun getCategory():Category{
//        launch {
//            val response = withContext(Dispatchers.IO) {
//                gitHubService.listRepos("thanhniencung")
//            }
//            Log.e("RETROFIT_CODE4FUNC", "${response.size}")
//        }
//    }
}
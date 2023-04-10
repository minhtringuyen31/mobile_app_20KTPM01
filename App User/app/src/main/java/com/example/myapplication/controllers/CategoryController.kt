package com.example.myapplication.controllers

import com.example.myapplication.modals.Category
import com.example.myapplication.services.CategoryService
import com.example.myapplication.viewmodels.CategoryViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CategoryController {
    private lateinit var  categories:List<Category>
    private lateinit var categoryViewModel:CategoryViewModel

}
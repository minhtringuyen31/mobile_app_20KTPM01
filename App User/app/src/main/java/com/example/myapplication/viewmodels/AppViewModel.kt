package com.example.myapplication.viewmodels

import android.view.View
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner

class AppViewModel:ViewModel() {
    private lateinit var categoryViewModel: CategoryViewModel
    private lateinit var promotionViewModel: PromotionViewModel
    private lateinit var productViewModel: ProductViewModel

     fun setUpViewModel(view: View,viewModelStoreOwner: ViewModelStoreOwner) {
        categoryViewModel = ViewModelProvider(viewModelStoreOwner)[CategoryViewModel::class.java]
        categoryViewModel.getCategories();
        productViewModel = ViewModelProvider(viewModelStoreOwner)[ProductViewModel::class.java]
        productViewModel.getProducts();
        promotionViewModel = ViewModelProvider(viewModelStoreOwner)[PromotionViewModel::class.java]
        promotionViewModel.getPromotions();
    }
    fun getCategoryViewModel():CategoryViewModel{
        return this.categoryViewModel
    }

    fun getProductViewModel():ProductViewModel{
        return this.productViewModel
    }

    fun getPromotionViewModel():PromotionViewModel{
        return this.promotionViewModel
    }



}
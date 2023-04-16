package com.example.myapplication.viewmodels

import android.view.View
import androidx.lifecycle.*
import com.example.myapplication.modals.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AppViewModel:ViewModel() {
    private  lateinit var  categoryViewModel: CategoryViewModel
    private lateinit  var promotionViewModel: PromotionViewModel
    private  lateinit var productViewModel: ProductViewModel
    private lateinit  var toppingViewModel:ToppingViewModel
    private  lateinit var cartItemViewModel:CartItemViewModel

     fun setUpViewModel(view: View,viewModelStoreOwner: ViewModelStoreOwner) {



         viewModelScope.launch {
             withContext(Dispatchers.Main) {
                 categoryViewModel = ViewModelProvider(viewModelStoreOwner)[CategoryViewModel::class.java]
                 categoryViewModel.getCategories()
                 productViewModel = ViewModelProvider(viewModelStoreOwner)[ProductViewModel::class.java]
                 productViewModel.getProducts()
                 promotionViewModel = ViewModelProvider(viewModelStoreOwner)[PromotionViewModel::class.java]
                 promotionViewModel.getPromotions()
                 toppingViewModel = ViewModelProvider(viewModelStoreOwner)[ToppingViewModel::class.java]
                 toppingViewModel.getToppings()
                 cartItemViewModel = ViewModelProvider(viewModelStoreOwner)[CartItemViewModel::class.java]
                 cartItemViewModel.getItemsCart()
                 println("Current view-model: ${Thread.currentThread().name}")
             }
         }
    }
    fun setUpCategoryViewModel(viewModelStoreOwner: ViewModelStoreOwner){
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                categoryViewModel = ViewModelProvider(viewModelStoreOwner)[CategoryViewModel::class.java]
                categoryViewModel.getCategories()
                println("Current view-model: ${Thread.currentThread().name}")
            }
        }

    }
    fun setUpProductViewModel(viewModelStoreOwner: ViewModelStoreOwner){
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                productViewModel = ViewModelProvider(viewModelStoreOwner)[ProductViewModel::class.java]
                productViewModel.getProducts()
                println("Current view-model: ${Thread.currentThread().name}")
            }
        }

    }
    fun setUpPromotionViewModel(viewModelStoreOwner: ViewModelStoreOwner){
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                promotionViewModel = ViewModelProvider(viewModelStoreOwner)[PromotionViewModel::class.java]
                promotionViewModel.getPromotions()
                println("Current viewmodel: ${Thread.currentThread().name}")
            }
        }



    }
    fun setUpCartItemViewModel(viewModelStoreOwner: ViewModelStoreOwner){
        viewModelScope.launch {
            withContext(Dispatchers.Main) {
                cartItemViewModel = ViewModelProvider(viewModelStoreOwner)[CartItemViewModel::class.java]
                cartItemViewModel.getItemsCart()
                println("Current view-model: ${Thread.currentThread().name}")
            }
        }


    }
    fun setUpToppingViewModel(viewModelStoreOwner: ViewModelStoreOwner){
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                toppingViewModel = ViewModelProvider(viewModelStoreOwner)[ToppingViewModel::class.java]
                toppingViewModel.getToppings()
                println("Current view-model: ${Thread.currentThread().name}")
            }
        }


    }


    fun getCategoryViewModel():CategoryViewModel{
        return this.categoryViewModel
    }

    fun getProductViewModel():ProductViewModel{
        return this.productViewModel
    }


    fun getPromotionViewMode():PromotionViewModel{
        return this.promotionViewModel
    }
    fun getToppingViewModel():ToppingViewModel{
        return this.toppingViewModel
    }
    fun getCartItemViewModel():CartItemViewModel{
        return this.cartItemViewModel
    }

    fun addtoCart(cartItem:CartItem) {
        cartItemViewModel.createCartItem(cartItem)
    }
    fun removeItemCart(id:Int){
        cartItemViewModel.deleteCartItem(id)
    }



}


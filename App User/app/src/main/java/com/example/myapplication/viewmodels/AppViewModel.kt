package com.example.myapplication.viewmodels

import android.view.View
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.viewModelScope
import com.example.myapplication.modals.CartItem
import com.example.myapplication.modals.FavProductItem
import com.example.myapplication.viewmodels.cart.CartItemViewModel
import com.example.myapplication.viewmodels.category.CategoryViewModel
import com.example.myapplication.viewmodels.order.OrderProductViewModel
import com.example.myapplication.viewmodels.order.OrderViewModel
import com.example.myapplication.viewmodels.product.FavProductViewModel
import com.example.myapplication.viewmodels.product.ProductViewModel
import com.example.myapplication.viewmodels.product.RatingViewModel
import com.example.myapplication.viewmodels.product.ToppingViewModel
import com.example.myapplication.viewmodels.promotion.PromotionViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AppViewModel:ViewModel() {
    private  lateinit var  categoryViewModel: CategoryViewModel
    private lateinit  var promotionViewModel: PromotionViewModel
    private  lateinit var productViewModel: ProductViewModel
    private lateinit  var toppingViewModel: ToppingViewModel
    private  lateinit var cartItemViewModel: CartItemViewModel
    private lateinit var ratingViewModel: RatingViewModel
    private lateinit var orderViewModel: OrderViewModel
    private lateinit var orderProductViewModel: OrderProductViewModel
    private lateinit var favProductViewModel: FavProductViewModel

     fun setUpViewModel(view: View,viewModelStoreOwner: ViewModelStoreOwner) {
         viewModelScope.launch {
             withContext(Dispatchers.Main) {
                 categoryViewModel = ViewModelProvider(viewModelStoreOwner)[CategoryViewModel::class.java]
                 categoryViewModel.getCategories()
                 productViewModel = ViewModelProvider(viewModelStoreOwner)[ProductViewModel::class.java]
                 productViewModel.getProducts()
                 productViewModel.getProductsSales()
                 promotionViewModel = ViewModelProvider(viewModelStoreOwner)[PromotionViewModel::class.java]
                 promotionViewModel.getPromotions()
                 toppingViewModel = ViewModelProvider(viewModelStoreOwner)[ToppingViewModel::class.java]
                 toppingViewModel.getToppings()
                 cartItemViewModel = ViewModelProvider(viewModelStoreOwner)[CartItemViewModel::class.java]
//                 cartItemViewModel.getItemsCart()
                 favProductViewModel = ViewModelProvider(viewModelStoreOwner)[FavProductViewModel::class.java]
             }
         }
    }
    fun setUpCategoryViewModel(viewModelStoreOwner: ViewModelStoreOwner){
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                categoryViewModel = ViewModelProvider(viewModelStoreOwner)[CategoryViewModel::class.java]
                categoryViewModel.getCategories()
            }
        }

    }
    fun setUpProductViewModel(viewModelStoreOwner: ViewModelStoreOwner){
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                productViewModel = ViewModelProvider(viewModelStoreOwner)[ProductViewModel::class.java]
                productViewModel.getProducts()
            }
        }

    }



    fun setUpPromotionViewModel(viewModelStoreOwner: ViewModelStoreOwner){
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                promotionViewModel = ViewModelProvider(viewModelStoreOwner)[PromotionViewModel::class.java]
                promotionViewModel.getPromotions()
            }
        }



    }
    fun setUpCartItemViewModel(viewModelStoreOwner: ViewModelStoreOwner, userId : Int){
        viewModelScope.launch {
            withContext(Dispatchers.Main) {
                cartItemViewModel = ViewModelProvider(viewModelStoreOwner)[CartItemViewModel::class.java]
                cartItemViewModel.getItemsCart(userId)
            }
        }
    }



    fun setUpOrderViewModel(viewModelStoreOwner: ViewModelStoreOwner, userId: Int){
        viewModelScope.launch {
            withContext(Dispatchers.Main) {
                orderViewModel = ViewModelProvider(viewModelStoreOwner)[OrderViewModel::class.java]
                orderViewModel.getAllOrder(userId)
            }
        }
    }

    fun getOrderViewModel(): OrderViewModel {
        return orderViewModel;
    }

    fun setUpFavProductViewModel(viewModelStoreOwner: ViewModelStoreOwner, userId: Int){
        viewModelScope.launch {
            withContext(Dispatchers.Main) {
                favProductViewModel = ViewModelProvider(viewModelStoreOwner)[FavProductViewModel::class.java]
                favProductViewModel.getAllFavProducts(userId)

            }
        }
    }

    fun getFavProductViewModel(): FavProductViewModel {
        return favProductViewModel;
    }


    fun setUpOrderProductViewModel(viewModelStoreOwner: ViewModelStoreOwner, orderId: Int){
        viewModelScope.launch {
            withContext(Dispatchers.Main) {
                orderProductViewModel = ViewModelProvider(viewModelStoreOwner)[OrderProductViewModel::class.java]
                orderProductViewModel.getAllProductOfOrder(orderId)
            }
        }
    }

    fun getOrderProductViewModel():OrderProductViewModel{
        return orderProductViewModel
    }

    fun setUpRatingViewMode(viewModelStoreOwner: ViewModelStoreOwner, productId:Int){
        viewModelScope.launch {
            withContext(Dispatchers.Main){
                ratingViewModel = ViewModelProvider(viewModelStoreOwner)[RatingViewModel::class.java]
                ratingViewModel.getRating(productId)
            }
        }
    }


    fun setUpToppingViewModel(viewModelStoreOwner: ViewModelStoreOwner){
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                toppingViewModel = ViewModelProvider(viewModelStoreOwner)[ToppingViewModel::class.java]
                toppingViewModel.getToppings()

            }
        }


    }

    fun getCategoryViewModel(): CategoryViewModel {
        return this.categoryViewModel
    }

    fun getProductViewModel(): ProductViewModel {
        return this.productViewModel
    }


    fun getPromotionViewMode(): PromotionViewModel {
        return this.promotionViewModel
    }
    fun getToppingViewModel(): ToppingViewModel {
        return this.toppingViewModel
    }
    fun getCartItemViewModel(): CartItemViewModel {
        return this.cartItemViewModel
    }
    fun addtoCart(cartItem:CartItem) {
        cartItemViewModel.createCartItem(cartItem)
    }
    fun removeItemCart(id:Int){
        cartItemViewModel.deleteCartItem(id)
    }
    fun removeAllCart(userID:Int){
        cartItemViewModel.removeAllCartItem(userID)
    }
    fun updateItemCart(id:Int,cartItem:CartItem){
        cartItemViewModel.updateCartItem(id,cartItem)
    }

    fun getRatingViewModel(): RatingViewModel {
        return this.ratingViewModel
    }

    fun addFavProduct(favProduct : FavProductItem){
        favProductViewModel.addFavProduct(favProduct)
    }

    fun removeFavProduct(favProduct: FavProductItem) {
        favProductViewModel.removeFavProduct(favProduct)
    }

    fun isExistedFavProduct(userId: Int, productId: Int){
        favProductViewModel.isExistedFacProduct(userId, productId)
        favProductViewModel.check
    }

    fun getRating(productId: Int){
        productViewModel.getRating(productId)
    }

}


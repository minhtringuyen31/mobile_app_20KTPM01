package com.example.myapplication.pages.apdaters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.modals.Product

class FavoriteProductListAdapter(
    private var favoriteProductList: ArrayList<Product>
): RecyclerView.Adapter<FavoriteProductListAdapter.ViewHolder>() {
    var onItemAddToCartClick:((Product) -> Unit)? = null

    inner class ViewHolder(listItemView: View):RecyclerView.ViewHolder(listItemView){
        val favoriteProductNameTV : TextView = listItemView.findViewById(R.id.favoriteProductNameTV)
        val favoriteProductRatingRB : RatingBar = listItemView.findViewById(R.id.favoriteProductRatingRB)
        val favoriteProductPriceTV : TextView = listItemView.findViewById(R.id.favoriteProductPriceTV)
        val favoriteProductDescriptionTv : TextView = listItemView.findViewById(R.id.favoriteProductDescriptionTV)
        val favoriteProductAddToCartBtn : TextView = listItemView.findViewById(R.id.favoriteProductAddToCartBtn)

        init {
            favoriteProductAddToCartBtn.setOnClickListener{
                onItemAddToCartClick?.invoke(favoriteProductList[position])
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//        TODO("Not yet implemented")
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val onGoingOrderView = inflater.inflate(R.layout.item_favorite_product, parent, false)
        return ViewHolder(onGoingOrderView)
    }

    override fun getItemCount(): Int {
//        TODO("Not yet implemented")
        return favoriteProductList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        TODO("Not yet implemented")
        val favoriteProduct: Product = favoriteProductList[position]
        val favProductName = holder.favoriteProductNameTV
        val favProductRatingValue = holder.favoriteProductRatingRB
        val favProductPrice = holder.favoriteProductPriceTV
        val favProductDescription = holder.favoriteProductDescriptionTv

        favProductName.text = favoriteProduct.getName()
        favProductPrice.text = favoriteProduct.getPrice_M().toString()
        favProductDescription.text = favoriteProduct.getDescription()
//        favProductRatingValue.rating = favoriteProduct
    }

}
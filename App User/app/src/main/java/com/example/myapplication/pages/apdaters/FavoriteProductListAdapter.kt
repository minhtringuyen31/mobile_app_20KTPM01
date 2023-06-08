package com.example.myapplication.pages.apdaters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import android.widget.ToggleButton
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.R
import com.example.myapplication.modals.Product

class FavoriteProductListAdapter(
    private var favoriteProductList: ArrayList<Product>
): RecyclerView.Adapter<FavoriteProductListAdapter.ViewHolder>() {
    var onItemClick:((Product)-> Unit)? = null
    var onItemAddToCartClick:((Product) -> Unit)? = null
    var onItemFavToggleClick:((Product) -> Unit)? = null

    inner class ViewHolder(listItemView: View):RecyclerView.ViewHolder(listItemView){
        val favoriteProductNameTV : TextView = listItemView.findViewById(R.id.favoriteProductNameTV)
        val favoriteProductRatingRB : RatingBar = listItemView.findViewById(R.id.favoriteProductRatingRB)
        val favoriteProductPriceTV : TextView = listItemView.findViewById(R.id.favoriteProductPriceTV)
        val favoriteProductDescriptionTv : TextView = listItemView.findViewById(R.id.favoriteProductDescriptionTV)
        val favoriteProductAddToCartBtn : TextView = listItemView.findViewById(R.id.favoriteProductAddToCartBtn)
        val favoriteProductImage : ImageView = listItemView.findViewById(R.id.favoriteProductImg)
        val favoriteProductToggleBtn : ToggleButton = listItemView.findViewById(R.id.favoriteToggleBtn)

        init {
            listItemView.setOnClickListener{
                onItemClick?.invoke(favoriteProductList[position])
            }
        }

        init {
            favoriteProductAddToCartBtn.setOnClickListener{
                onItemAddToCartClick?.invoke(favoriteProductList[position])
            }
        }

        init {
            favoriteProductToggleBtn.setOnClickListener{
                onItemFavToggleClick?.invoke(favoriteProductList[position])
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//        TODO("Not yet implemented")
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val favoriteProductView = inflater.inflate(R.layout.item_favorite_product, parent, false)
        return ViewHolder(favoriteProductView)
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
        val favProductImg = holder.favoriteProductImage
        val favToggleButton = holder.favoriteProductToggleBtn

        favProductName.text = favoriteProduct.getName()
        favProductPrice.text = favoriteProduct.getPrice_M().toString()
        favProductDescription.text = favoriteProduct.getDescription()
        favToggleButton.isChecked = true
//        favProductRatingValue.rating = favoriteProduct.get
        Glide.with(holder.itemView)
            .load(favoriteProduct.getImage()).fitCenter()
            .into(favProductImg)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun addFavProduct(product:ArrayList<Product>){
        favoriteProductList.apply {
            clear()
            favoriteProductList.addAll(product)
            notifyDataSetChanged()
        }
    }

}
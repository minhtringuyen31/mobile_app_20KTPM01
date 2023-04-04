package com.example.myapplication.pages

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.modals.Category
import com.example.myapplication.modals.Product

class ProductList : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_list)
    }
}


class ProductListAdapter(
    private var productList: ArrayList<Product>
): RecyclerView.Adapter<ProductListAdapter.ViewHolder>() {
    var onItemClick:((Product) -> Unit)? = null
    inner class ViewHolder(listItemView: View): RecyclerView.ViewHolder(listItemView){
        val productImageIV: ImageView = listItemView.findViewById(R.id.productImageIV)
        val productNameTV: TextView = listItemView.findViewById(R.id.productNameTV)
        val productPriceTV: TextView = listItemView.findViewById(R.id.productPriceTV)
//        val productDescriptionTV: TextView = listItemView.findViewById(R.id.productDescriptionTV)

        init {
            listItemView.setOnClickListener{
                onItemClick?.invoke(productList[adapterPosition])
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//        TODO("Not yet implemented")
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val productView = inflater.inflate(R.layout.product_list_grid_layout_item, parent,false)
        return ViewHolder(productView)
    }


    override fun getItemCount(): Int {
//        TODO("Not yet implemented")
        return productList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        TODO("Not yet implemented")
        val product: Product = productList[position]
        val productName = holder.productNameTV
        val productImage = holder.productImageIV
        val productPrice = holder.productPriceTV
//        val productDescription = holder.productDescriptionTV
        productName.text = product.name
        productPrice.text = product.price
//        productDescription.text = product.description
        productImage.setImageResource(product.image)
//        productImage.setImageURI(Uri.parse("/res/drawable/$productImage"))
    }
}

class CategoryListAdapter(
    private var categoryList: ArrayList<Category>
): RecyclerView.Adapter<CategoryListAdapter.ViewHolder>(){
    var onItemClick:((Category)->Unit)? = null
    inner class ViewHolder(listItemView: View): RecyclerView.ViewHolder(listItemView){
        val categoryImageIV: ImageView =listItemView.findViewById(R.id.categoryImageIV)
        val categoryNameTV: TextView = listItemView.findViewById(R.id.categoryName)

        init {
            listItemView.setOnClickListener{
                onItemClick?.invoke(categoryList[position])
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//        TODO("Not yet implemented")
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val categoryView = inflater.inflate(R.layout.product_category_item, parent, false)
        return ViewHolder(categoryView)
    }

    override fun getItemCount(): Int {
//        TODO("Not yet implemented")
        return categoryList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        TODO("Not yet implemented")
        val category: Category = categoryList[position]
        val categoryName = holder.categoryNameTV
        categoryName.text = category.name
        val categoryImage = holder.categoryImageIV
        categoryImage.setImageResource((R.drawable.ic_launcher_background))
    }
}
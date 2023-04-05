package com.example.myapplication.pages

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.modals.Category
import com.example.myapplication.modals.Product

class ProductList : AppCompatActivity() {
    private var currentCategory: TextView? = null
    private var productListAdapter : ProductListAdapter?= null
    private var categoryListAdapter : CategoryListAdapter?= null
    
    private val listProduct = arrayListOf(
        Product("Product 1", "35,000 VNĐ",R.drawable.product1, "This is product 1"),
        Product("Product 2", "35,000 VNĐ",R.drawable.product2, "This is product 2"),
        Product("Product 3", "35,000 VNĐ",R.drawable.product3, "This is product 3"),
        Product("Product 4", "35,000 VNĐ", R.drawable.product4, "This is product 4"),
        Product("Product 4", "35,000 VNĐ", R.drawable.product5, "This is product 4"),
        Product("Product 4", "35,000 VNĐ", R.drawable.product6, "This is product 4"),
        Product("Product 4", "35,000 VNĐ", R.drawable.product7, "This is product 4"),
        Product("Product 4", "35,000 VNĐ", R.drawable.product8, "This is product 4"),
        Product("Product 4", "35,000 VNĐ", R.drawable.product9, "This is product 4"),
        Product("Product 4", "35,000 VNĐ", R.drawable.product10, "This is product 4"),
        Product("Product 4", "35,000 VNĐ", R.drawable.product11, "This is product 4")
    )
    private val listCategory = arrayListOf(
        Category("CÀ PHÊ", R.drawable.category_coffee),
        Category("TRÀ", R.drawable.category_tea),
        Category("PHINDI", R.drawable.category_coffee),
        Category("FREEZE", R.drawable.category_freeze),
        Category("KHÁC",R.drawable.category_other)
    )
    private var productRecyclerView: RecyclerView? = null
    private var categoryRecyclerView: RecyclerView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_list)

        initUI()
        setUpCategoryRecyclerAdapter(listCategory)
        val isLinearLayoutManager: Boolean = true
        setUpProductRecyclerAdapter(listProduct, isLinearLayoutManager!!)
        if (isLinearLayoutManager as Boolean)
            productRecyclerView!!.layoutManager = LinearLayoutManager(this)
        else
            productRecyclerView!!.layoutManager = GridLayoutManager(this, 2)

    }

    private fun initUI(){
        currentCategory = findViewById(R.id.currentCategoryTV)
        categoryRecyclerView = findViewById(R.id.listCategoryRV)
        productRecyclerView = findViewById(R.id.listProductRV)
    }

    private fun setUpProductRecyclerAdapter(data: ArrayList<Product>, isLinearLayoutManager: Boolean) {
        productListAdapter = ProductListAdapter(data, isLinearLayoutManager!!)
        productRecyclerView!!.adapter = productListAdapter
        productListAdapter!!.onItemClick = { product ->
            val intent = Intent(
                this@ProductList,
                ProductDetail::class.java
            )
            intent.putExtra("name", product.name)
//            intent.putExtra("image", product.image)
            intent.putExtra("price", product.price)
            intent.putExtra("description", product.description)
            startActivity(intent)
        }
    }

    private fun setUpCategoryRecyclerAdapter(data: ArrayList<Category>) {
        categoryListAdapter = CategoryListAdapter(data)
        categoryRecyclerView!!.adapter = categoryListAdapter
        categoryRecyclerView!!.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        categoryListAdapter!!.onItemClick = { category ->
            currentCategory!!.text = category.name
            //Handle set product by category type

        }
    }
}


class ProductListAdapter(
    private var productList: ArrayList<Product>,
    private var isLinearLayoutManager: Boolean
): RecyclerView.Adapter<ProductListAdapter.ViewHolder>() {
    var onItemClick:((Product) -> Unit)? = null
    inner class ViewHolder(listItemView: View): RecyclerView.ViewHolder(listItemView){
        val productImageIV: ImageView = listItemView.findViewById(R.id.productImageIV)
        val productNameTV: TextView = listItemView.findViewById(R.id.productNameTV)
        val productPriceTV: TextView = listItemView.findViewById(R.id.productPriceTV)
        val productDescriptionTV: TextView = listItemView.findViewById(R.id.productDescriptionTV)

        init {
            listItemView.setOnClickListener{
                onItemClick?.invoke(productList[adapterPosition])
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        var productView: View? = null
        if (isLinearLayoutManager) {
            productView = inflater.inflate(R.layout.product_list_linear_layout_item, parent, false)
        } else {
            productView = inflater.inflate(R.layout.product_list_grid_layout_item, parent, false)
        }
        return ViewHolder(productView)
    }


    override fun getItemCount(): Int {
        return productList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val product: Product = productList[position]
        val productName = holder.productNameTV
        val productImage = holder.productImageIV
        val productPrice = holder.productPriceTV
        val productDescription = holder.productDescriptionTV
        productName.text = product.name
        productPrice.text = product.price
        productDescription.text = product.description
        productImage.setImageResource(product.image)
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
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val categoryView = inflater.inflate(R.layout.product_category_item, parent, false)
        return ViewHolder(categoryView)
    }

    override fun getItemCount(): Int {
        return categoryList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val category: Category = categoryList[position]
        val categoryName = holder.categoryNameTV
        categoryName.text = category.name
        val categoryImage = holder.categoryImageIV
        categoryImage.setImageResource(category.image)
    }
}
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
        Product("1","Capuchino","Đồ uống",50.0,"M",12.0,12.0,12.0,"Blabla","images/product/product01.png",true,"123","12/02/2002","12.",1),
        Product("2","Trà Sen Vàng","Đồ uống",58.0,"M",12.0,12.0,12.0,"Blabla","images/product/product02.png",true,"123","12/02/2002","12.",1),
        Product("3","Phin Sữa đá","Đồ uống",30.0,"M",12.0,12.0,12.0,"Blabla","images/product/product03.png",true,"123","12/02/2002","12.",1),
        Product("4","Freeze Trà Xanh","Đồ uống",25.0,"M",12.0,12.0,12.0,"Blabla","images/product/product04.png",true,"123","12/02/2002","12.",1),
        Product("5","Phidil Hạnh Nhân","Đồ uống",64.0,"M",12.0,12.0,12.0,"Blabla","images/product/product10.png",true,"123","12/02/2002","12.",1),
        Product("6","Phildi Chôc","Đồ uống",56.0,"M",12.0,12.0,12.0,"Blabla","images/product/product14.png",true,"123","12/02/2002","12.",1),
        Product("5","Phidi  l Hạnh Nhân","Đồ uống",64.0,"M",12.0,12.0,12.0,"Blabla","images/product/product15.png",true,"123","12/02/2002","12.",1),
        Product("6","Phildi Chôc","Đồ uống",56.0,"M",12.0,12.0,12.0,"Blabla","images/product/product16.png",true,"123","12/02/2002","12.",1),
        Product("5","Phidil Hạnh Nhân","Đồ uống",64.0,"M",12.0,12.0,12.0,"Blabla","images/product/product15.png",true,"123","12/02/2002","12.",1),
        Product("6","Phildi Chôc","Đồ uống",56.0,"M",12.0,12.0,12.0,"Blabla","images/product/product20.png",true,"123","12/02/2002","12.",1)
    )
    private val listCategory = arrayListOf(
        Category("#caphett","Cà phê","images/categories/Caphett.png"),
        Category("#daxay","Đá xay","images/categories/Daxay.png"),
        Category("#phidi","Phindi","images/categories/Phidi.png"),
        Category("#tra","Trà","images/categories/Tra.png"),
        Category("#douongkhac","Khác","images/categories/Douongkhac.png")

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
            intent.putExtra("name", product.getName())
//            intent.putExtra("image", product.image)
            intent.putExtra("price", product.getPrice())
            intent.putExtra("description", product.getDescription())
            startActivity(intent)
        }
    }

    private fun setUpCategoryRecyclerAdapter(data: ArrayList<Category>) {
        categoryListAdapter = CategoryListAdapter(data)
        categoryRecyclerView!!.adapter = categoryListAdapter
        categoryRecyclerView!!.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        categoryListAdapter!!.onItemClick = { category ->
            currentCategory!!.text = category.getName()
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
        productName.text = product.getName()
        productPrice.text = product.getPrice().toString()
        productDescription.text = product.getDescription()
//        productImage.setImageResource(product.image)
        productImage.setImageBitmap(product.getDecodeImage())
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
        categoryName.text = category.getName()
        val categoryImage = holder.categoryImageIV
//        categoryImage.setImageResource(category.image)
        categoryImage.setImageBitmap(category.getDecodeImage())
    }
}
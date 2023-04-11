package com.example.myapplication.pages.apdaters


import android.graphics.Rect
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.R
import com.example.myapplication.modals.Product
import com.example.myapplication.pages.fragments.Homepage
import com.example.myapplication.pages.fragments.ProductDetail


class ProductApdapter(private val context: Homepage, private val products: ArrayList<Product>) :
    RecyclerView.Adapter<ProductApdapter.ViewHolder>() {
    lateinit var view:View
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageView = view.findViewById(R.id.image_product) as ImageView
        val name = view.findViewById(R.id.name_product) as TextView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        view = LayoutInflater.from(parent.context).inflate(R.layout.item_product, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(holder.itemView)
            .load(products[position].getImage()).fitCenter()
            .into(holder.imageView)
        holder.name.text = products[position].getName();
        holder.itemView.setOnClickListener {

            val bundle = Bundle()
            bundle.putString("name", products[position].getName())
            bundle.putString("image", products[position].getImage())
            bundle.putString("price", products[position].getPrice_M().toString())
            bundle.putString("description", products[position].getDescription())
            val productDetail = ProductDetail()
            productDetail.arguments = bundle
            (view.context as FragmentActivity).supportFragmentManager
                .beginTransaction()
                .replace(R.id.flFragment, productDetail).addToBackStack(null)
                .commit()

        }
    }

    override fun getItemCount(): Int {
        return products.size
    }
    fun addProducts(products: ArrayList<Product>) {
        this.products.apply {
            clear();
            addAll(products)
        }
    }



}

class GridSpacingItemDecoration(
    private val spanCount: Int,
    private val spacing: Int,
    private val includeEdge: Boolean
) :
    RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val position = parent.getChildAdapterPosition(view) // item position
        val column = position % spanCount // item column
        if (includeEdge) {
            outRect.left =
                spacing - column * spacing / spanCount // spacing - column * ((1f / spanCount) * spacing)
            outRect.right =
                (column + 1) * spacing / spanCount // (column + 1) * ((1f / spanCount) * spacing)
            if (position < spanCount) { // top edge
                outRect.top = spacing
            }
            outRect.bottom = spacing // item bottom
        } else {
            outRect.left = column * spacing / spanCount // column * ((1f / spanCount) * spacing)
            outRect.right =
                spacing - (column + 1) * spacing / spanCount // spacing - (column + 1) * ((1f /    spanCount) * spacing)
            if (position >= spanCount) {
                outRect.top = spacing // item top
            }
        }
    }

}


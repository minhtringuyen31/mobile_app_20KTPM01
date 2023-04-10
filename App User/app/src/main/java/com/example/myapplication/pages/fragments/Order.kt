package com.example.myapplication.pages.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.modals.Category
import com.example.myapplication.modals.Product
import com.example.myapplication.pages.CategoryListAdapter
import com.example.myapplication.pages.ProductDetail
import com.example.myapplication.pages.ProductListAdapter

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Order.newInstance] factory method to
 * create an instance of this fragment.
 */
class Order : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var currentCategory: TextView? = null
    private var productListAdapter : ProductListAdapter?= null
    private var categoryListAdapter : CategoryListAdapter?= null
    private val listCategory = arrayListOf(
        Category(1,"Cà phê","images/categories/Caphett.png",0),
        Category(2,"Đá xay","images/categories/Daxay.png",0),
        Category(3,"Phindi","images/categories/Phidi.png",0),
        Category(4,"Trà","images/categories/Tra.png",0),
        Category(5,"Khác","images/categories/Douongkhac.png",0)
    )
    private var productRecyclerView: RecyclerView? = null
    private var categoryRecyclerView: RecyclerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       val view =inflater.inflate(R.layout.fragment_order, container, false);

        initUI(view)
        setUpCategoryRecyclerAdapter(view,listCategory)
        val isLinearLayoutManager: Boolean = true
//        setUpProductRecyclerAdapter(view,listProduct,isLinearLayoutManager!!)
        if (isLinearLayoutManager as Boolean)
            productRecyclerView!!.layoutManager = LinearLayoutManager(context)
        else
            productRecyclerView!!.layoutManager = GridLayoutManager(context, 2)
        return view;
    }
    private fun initUI(view:View){
        currentCategory = view.findViewById(R.id.currentCategoryTV)
        categoryRecyclerView = view.findViewById(R.id.listCategoryRV)
        productRecyclerView = view.findViewById(R.id.listProductRV)
    }

    private fun setUpProductRecyclerAdapter(view:View,data: ArrayList<Product>, isLinearLayoutManager: Boolean) {
        productListAdapter = ProductListAdapter(data, isLinearLayoutManager!!)
        productRecyclerView!!.adapter = productListAdapter
        productListAdapter!!.onItemClick = { product ->
            val intent = Intent(
                view.context,
                ProductDetail::class.java
            )
            intent.putExtra("name", product.getName())
            intent.putExtra("image", product.getImage())
            intent.putExtra("price", product.getPrice_M())
            intent.putExtra("description", product.getDescription())
            startActivity(intent)
        }
    }

    private fun setUpCategoryRecyclerAdapter(view: View, data: ArrayList<Category>) {
        categoryListAdapter = CategoryListAdapter(data)
        categoryRecyclerView!!.adapter = categoryListAdapter
        categoryRecyclerView!!.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        categoryListAdapter!!.onItemClick = { category ->
            currentCategory!!.text = category.getName()
            //Handle set product by category type

        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Order.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Order().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
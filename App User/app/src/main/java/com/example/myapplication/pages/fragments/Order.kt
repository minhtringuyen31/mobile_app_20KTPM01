package com.example.myapplication.pages.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.MainActivity
import com.example.myapplication.R
import com.example.myapplication.modals.Category
import com.example.myapplication.modals.Product
import com.example.myapplication.pages.apdaters.CategoryListAdapter
import com.example.myapplication.pages.apdaters.ProductListAdapter
import com.example.myapplication.viewmodels.AppViewModel
import com.example.myapplication.viewmodels.CategoryViewModel
import com.example.myapplication.viewmodels.ProductCartViewModel
import com.example.myapplication.viewmodels.ProductViewModel

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
    private val appModel: AppViewModel by activityViewModels()
    private lateinit var currentCategory: TextView
    private lateinit var productListAdapter : ProductListAdapter
    private lateinit var categoryListAdapter : CategoryListAdapter
    private lateinit var productRecyclerView: RecyclerView
    private lateinit var categoryRecyclerView: RecyclerView
    private lateinit var categoryViewModel: CategoryViewModel
    private lateinit var productViewModel: ProductViewModel
    private val productCartViewModel: ProductCartViewModel by activityViewModels()
    private lateinit var  searchView:SearchView;


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       val view =inflater.inflate(R.layout.fragment_order, container, false)

        setUpViewModel(view)
        initUI(view)
        setUpObserve(view)
        return view
    }
    private fun setUpObserve(view:View){
        categoryViewModel.categories.observe(viewLifecycleOwner) {
            val categories = it as ArrayList<Category>
            if (categories.isEmpty()) {

                setUpCategoryRecyclerAdapter(view, arrayListOf())
            } else {
                setUpCategoryRecyclerAdapter(view,categories)
            }
        }

        productViewModel.products.observe(viewLifecycleOwner) {
            val products = it as ArrayList<Product>
            if (products.isEmpty()) {
                setUpProductRecyclerAdapter(view, arrayListOf(),true)

            } else {
                setUpProductRecyclerAdapter(view,products,true)

            }

        }

    }
    private fun setUpViewModel(view: View){
        appModel.setUpViewModel(view,this)
        categoryViewModel =  appModel.getCategoryViewModel()
        productViewModel =  appModel.getProductViewModel()

    }
    private fun initUI(view:View){
        (activity as MainActivity).setSelectedIcon(1)
        (activity as MainActivity).showToolbarAndNavigationBar(true)
        currentCategory = view.findViewById(R.id.currentCategoryTV)
        categoryRecyclerView = view.findViewById(R.id.listCategoryRV)
        productRecyclerView = view.findViewById(R.id.listProductRV)
        searchView = view.findViewById(R.id.searchView)


        val isLinearLayoutManager = true
//        setUpProductRecyclerAdapter(view,listProduct,isLinearLayoutManager!!)
        if (isLinearLayoutManager)
            productRecyclerView.layoutManager = LinearLayoutManager(context)
        else
            productRecyclerView.layoutManager = GridLayoutManager(context, 2)

    }

    private fun setUpProductRecyclerAdapter(view:View,data: ArrayList<Product>, isLinearLayoutManager: Boolean) {
        productListAdapter = ProductListAdapter(data, isLinearLayoutManager)
        productRecyclerView.adapter = productListAdapter

        productListAdapter.onItemClick = { product ->
            productCartViewModel.setId(product.getId())
            productCartViewModel.setName(product.getName())
            productCartViewModel.setImage(product.getImage())
            productCartViewModel.setDescription(product.getDescription())
            productCartViewModel.setPriceL(product.getPrice_L().toDouble())
            productCartViewModel.setPriceM(product.getPrice_M().toDouble())
            productCartViewModel.setPriceS(product.getPrice_S().toDouble())
            productCartViewModel.setCategoryId(product.getCategory_id())

            (view.context as FragmentActivity).supportFragmentManager
                .beginTransaction()
                .replace(R.id.flFragment, ProductDetail()).addToBackStack(null)
                .commit()
        }

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            // Override onQueryTextSubmit method which is call when submit query is searched
            @SuppressLint("NotifyDataSetChanged")
            override fun onQueryTextSubmit(query: String): Boolean {
                // If the list contains the search query than filter the adapter
                // using the filter method with the query as its argument
                val regex = ".*${query.replace(" ", ".*")}.*".toRegex(RegexOption.IGNORE_CASE)
                val result =data.filter {
                    it.getName().matches(regex)
                }
                productListAdapter = ProductListAdapter(result as ArrayList<Product>, isLinearLayoutManager)
                productRecyclerView.adapter = productListAdapter
                productListAdapter.onItemClick = { product ->
                    productCartViewModel.setId(product.getId())
                    productCartViewModel.setName(product.getName())
                    productCartViewModel.setImage(product.getImage())
                    productCartViewModel.setDescription(product.getDescription())
                    productCartViewModel.setPriceL(product.getPrice_L().toDouble())
                    productCartViewModel.setPriceM(product.getPrice_M().toDouble())
                    productCartViewModel.setPriceS(product.getPrice_S().toDouble())
                    productCartViewModel.setCategoryId(product.getCategory_id())

                    (view.context as FragmentActivity).supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.flFragment, ProductDetail()).addToBackStack(null)
                        .commit()
                }
                return false;
            }

            // This method is overridden to filter the adapter according
            // to a search query when the user is typing search
            @SuppressLint("NotifyDataSetChanged")
            override fun onQueryTextChange(newText: String): Boolean {

                val regex = ".*${newText.replace(" ", ".*")}.*".toRegex(RegexOption.IGNORE_CASE)
                val result =data.filter {
                    it.getName().matches(regex)
                }

                productListAdapter = ProductListAdapter(result as ArrayList<Product>, isLinearLayoutManager)
                productRecyclerView.adapter = productListAdapter
                productListAdapter.onItemClick = { product ->
                    productCartViewModel.setId(product.getId())
                    productCartViewModel.setName(product.getName())
                    productCartViewModel.setImage(product.getImage())
                    productCartViewModel.setDescription(product.getDescription())
                    productCartViewModel.setPriceL(product.getPrice_L().toDouble())
                    productCartViewModel.setPriceM(product.getPrice_M().toDouble())
                    productCartViewModel.setPriceS(product.getPrice_S().toDouble())
                    productCartViewModel.setCategoryId(product.getCategory_id())

                    (view.context as FragmentActivity).supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.flFragment, ProductDetail()).addToBackStack(null)
                        .commit()
                }
                return false;
            }
        })

    }

    private fun setUpCategoryRecyclerAdapter(view: View, data: ArrayList<Category>) {
        categoryListAdapter = CategoryListAdapter(data)
        categoryRecyclerView.adapter = categoryListAdapter
        categoryRecyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        categoryListAdapter.onItemClick = { category ->
            currentCategory.text = category.getName()
            //Handle set product by category type

            productViewModel.products.observe(viewLifecycleOwner) {
                val products = it as ArrayList<Product>
                var productsWithCategoryOne = it as ArrayList<Product>
                productsWithCategoryOne = products.filter { it.getCategory_id() == category.getId() } as ArrayList<Product>
                if (productsWithCategoryOne.isEmpty()) {
                    setUpProductRecyclerAdapter(view, arrayListOf(),true)

                } else {
                    setUpProductRecyclerAdapter(view,productsWithCategoryOne,true)
                }
            }

//            (view.context as FragmentActivity).supportFragmentManager
//                .beginTransaction()
//                .replace(R.id.flFragment, Order()).addToBackStack(null)
//                .commit()

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
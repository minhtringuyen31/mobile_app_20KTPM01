package com.example.myapplication.pages.fragments

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.andremion.counterfab.CounterFab
import com.example.myapplication.R
import com.example.myapplication.modals.CartItem
import com.example.myapplication.modals.FavProductItem
import com.example.myapplication.modals.Order
import com.example.myapplication.modals.Product
import com.example.myapplication.pages.apdaters.FavoriteProductListAdapter
import com.example.myapplication.viewmodels.AppViewModel
import com.example.myapplication.viewmodels.sharedata.ProductCartViewModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FavoriteProduct.newInstance] factory method to
 * create an instance of this fragment.
 */
class FavoriteProduct : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var favBackBtn : ImageButton
    private lateinit var favProductListRecyclerView: RecyclerView
    private lateinit var favProductListAdapter: FavoriteProductListAdapter
    private val appModel: AppViewModel by activityViewModels()
    private lateinit var layoutManager: RecyclerView.LayoutManager
    private lateinit var backBtn : Button
    private val productCartViewModel: ProductCartViewModel by activityViewModels()


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
        // Inflate the layout for this fragment
        val view =inflater.inflate(R.layout.fragment_favorite_product, container, false)

        println("Favorite Product")

        val sharedPreferences: SharedPreferences =
            view.context.getSharedPreferences("user", Context.MODE_PRIVATE)
        val userId = sharedPreferences.getString("userID", null)
        if(userId!=null){
            setUpObserve(userId.toString().toInt())
        }
        initUI(view)

        return view

    }

    private fun initUI(view: View){
        favBackBtn = view.findViewById(R.id.favBackBtn)
        favProductListRecyclerView = view.findViewById(R.id.favoriteProductListRV)
        favProductListAdapter = FavoriteProductListAdapter(arrayListOf())
        layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        favProductListRecyclerView.layoutManager = layoutManager
        favProductListRecyclerView.adapter = favProductListAdapter
        favProductListRecyclerView.addItemDecoration(
            DividerItemDecoration(
                context,
                DividerItemDecoration.VERTICAL
            )
        )
        favProductListAdapter.onItemAddToCartClick = {product ->

            val sharedPreferences =
                view.context.getSharedPreferences("cart", AppCompatActivity.MODE_PRIVATE)

            val gson = Gson()
            val type: Type = object : TypeToken<ArrayList<Int>>() {}.type
            val carts = sharedPreferences.getString("productID", null)
            var dataItem = gson.fromJson<ArrayList<Int>>(carts, type);

            if (dataItem == null) {
                dataItem = ArrayList<Int>()
            }
            if (dataItem.contains(product.getId()) && dataItem.isNotEmpty()) {
                Toast.makeText(
                    activity, "Sản phẩm đã tồn tại trong giỏ hàng",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                val sharedPreferencesUser =
                    view.context.getSharedPreferences("user", AppCompatActivity.MODE_PRIVATE)
                val userID = sharedPreferencesUser.getString("userID", "")
                val notes = ""
                val cartItem = CartItem(
                    userID!!.toInt(),
                    userID!!.toInt(),
                    product.getId(),
                    1,
                    "M",
                    product.getPrice_M().toDouble(),
                    "",
                    product.getName(),
                    product.getDescription(),
                    product.getImage(),
                    product.getCategory_id(),
                    notes
                )
                appModel.addtoCart(cartItem)
                Toast.makeText(
                    activity, "Thêm thành công vào giỏ hàng",
                    Toast.LENGTH_SHORT
                ).show()
                val myFragment = parentFragmentManager.findFragmentByTag("Homepage") as Homepage?
                myFragment?.view?.findViewById<CounterFab>(R.id.fabTwo)?.increase()
                (view.context as FragmentActivity).supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.flFragment, Order(), "Order").addToBackStack(null)
                    .commit()
                val tempData = carts;
                dataItem.add(product.getId());
                sharedPreferences.edit().putString("productID", dataItem.toString()).apply()
            }
        }
        favProductListAdapter.onItemClick = {product ->
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
        favProductListAdapter.onItemFavToggleClick ={product ->
            val sharedPreferences: SharedPreferences =
                view.context.getSharedPreferences("user", Context.MODE_PRIVATE)
            val curUser = sharedPreferences.getString("userID", null)
            var favProduct = FavProductItem(0,0)
            var curProduct = productCartViewModel.getId()
            if(curUser!=null){
                favProduct = FavProductItem(curUser.toString().toInt(), productCartViewModel.getId())
            }
            println("fav $curUser +  $curProduct")
            appModel.removeFavProduct(favProduct)
        }


    }

    @SuppressLint("NotifyDataSetChanged")
    private fun setUpObserve(userId: Int){
        appModel.setUpFavProductViewModel(this, userId);
        appModel.getFavProductViewModel().products.observe(viewLifecycleOwner){
            favProductListAdapter.addFavProduct(it as ArrayList<Product>) ;
            favProductListAdapter.notifyDataSetChanged();
        }
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FavoriteProduct.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FavoriteProduct().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
package com.example.myapplication.pages.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.MainActivity
import com.example.myapplication.R
import com.example.myapplication.modals.CartItem
import com.example.myapplication.modals.Product
import com.example.myapplication.pages.apdaters.CartApdapter
import com.example.myapplication.pages.apdaters.interfaces.OnItemClickListener
import com.example.myapplication.viewmodels.AppViewModel
import com.example.myapplication.viewmodels.ProductCartViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Cart.newInstance] factory method to
 * create an instance of this fragment.
 */
class Cart : Fragment(), OnItemClickListener {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var btnPlaceOrder:TextView
    private lateinit var cartAdapter:CartApdapter
    private lateinit var cartItemRecyclerView: RecyclerView
    private lateinit var view:View
    private val appModel: AppViewModel by activityViewModels()
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
    ): View {
        view=inflater.inflate(R.layout.fragment_cart, container, false)
        // Inflate the layout for this fragment
        setUpViewModel()
        initUI(view)
        setupObserve()
        return view
    }
    private fun setUpViewModel(){
        (activity as MainActivity).showToolbarAndNavigationBar(false)
        (activity as MainActivity).showNavigationBar(true)
        appModel.setUpCartItemViewModel(this)
    }
    private fun initUI(view:View){
        btnPlaceOrder = view.findViewById(R.id.btnPlaceOrder)
        cartItemRecyclerView = view.findViewById(R.id.cartItemRecylView)
        cartAdapter = CartApdapter(arrayListOf(),this)
        cartItemRecyclerView.layoutManager =  LinearLayoutManager(context)
        cartItemRecyclerView.adapter=cartAdapter
    }
    @SuppressLint("NotifyDataSetChanged")
    fun setupObserve(){
        appModel.getCartItemViewModel().cartItems.observe(viewLifecycleOwner) {
                val cartItem = it as ArrayList<CartItem>
                cartAdapter.addCartItem(cartItem)
                cartAdapter.notifyDataSetChanged()
        }
        btnPlaceOrder.setOnClickListener {
            (view.context as FragmentActivity).supportFragmentManager
                .beginTransaction()
                .replace(R.id.flFragment, Checkout()).addToBackStack(null)
                .commit()

        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Cart.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Cart().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
    override fun onItemClick(position: Int, product: Product) {
        TODO("Not yet implemented")
    }
    @SuppressLint("NotifyDataSetChanged")
    override fun onCartItemClick(position: Int, cartItem: CartItem) {
        appModel.removeItemCart(cartItem.getId())
        cartAdapter.notifyItemRemoved(position);
        appModel.setUpCartItemViewModel(this)
        cartAdapter.notifyDataSetChanged()
        Toast.makeText(
            activity, "Delete successfully",
            Toast.LENGTH_LONG).show()
    }

    override fun onCartItemClickUpdate(position: Int, cartItem: CartItem) {


        appModel.getProductViewModel().getProduct(cartItem.getProductId())
        appModel.getProductViewModel().product.observe(this) {
            val product = it;
            productCartViewModel.setId(product.getId())
            productCartViewModel.setName(product.getName())
            productCartViewModel.setImage(product.getImage())
            productCartViewModel.setDescription(product.getDescription())
            productCartViewModel.setPriceL(product.getPrice_L().toDouble())
            productCartViewModel.setPriceM(product.getPrice_M().toDouble())
            productCartViewModel.setPriceS(product.getPrice_S().toDouble())
            productCartViewModel.setCategoryId(product.getCategory_id())
            productCartViewModel.setTopping(cartItem.getTopping())
            productCartViewModel.setPrice(cartItem.getPrice())
            (view.context as FragmentActivity).supportFragmentManager
                .beginTransaction()
                .replace(R.id.flFragment, ProductDetail()).addToBackStack(null)
                .commit()
        }

        }





}
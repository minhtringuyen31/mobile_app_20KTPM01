package com.example.myapplication.pages.fragments

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import cn.pedant.SweetAlert.SweetAlertDialog
import com.example.myapplication.MainActivity
import com.example.myapplication.R
import com.example.myapplication.modals.CartItem
import com.example.myapplication.modals.Product
import com.example.myapplication.pages.apdaters.CartApdapter
import com.example.myapplication.pages.apdaters.interfaces.OnItemClickListener
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
 * Use the [Cart.newInstance] factory method to
 * create an instance of this fragment.
 */
class Cart : Fragment(), OnItemClickListener {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var btnPlaceOrder:TextView
    private lateinit var cartAdapter: CartApdapter
    private lateinit var cartItemRecyclerView: RecyclerView
    private lateinit var icon_deletecard:TextView
    private lateinit var view:View
    private val appModel: AppViewModel by activityViewModels()
    private val productCartViewModel: ProductCartViewModel by activityViewModels()
    private lateinit var btnback:ImageView
    private lateinit var emptyList:ImageView
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
        val sharedPreferences: SharedPreferences =
            view.context.getSharedPreferences("user", Context.MODE_PRIVATE)
        val userId = sharedPreferences.getString("userID", null)
        if(userId!=null)
        {
            appModel.setUpCartItemViewModel(this, userId.toString().toInt())
        }
        (activity as MainActivity).showToolbarAndNavigationBar(false)
        (activity as MainActivity).showNavigationBar(true)

    }
    private fun initUI(view:View){
        btnPlaceOrder = view.findViewById(R.id.btnPlaceOrder)
        cartItemRecyclerView = view.findViewById(R.id.cartItemRecylView)
        cartAdapter = CartApdapter(arrayListOf(),this)
        cartItemRecyclerView.layoutManager =  LinearLayoutManager(context)
        cartItemRecyclerView.adapter=cartAdapter
        btnback = view.findViewById(R.id.back_btn)
        emptyList = view.findViewById(R.id.emptyList)
        icon_deletecard = view.findViewById(R.id.icon_deletecard)
    }
    @SuppressLint("NotifyDataSetChanged")
    fun setupObserve(){
        appModel.getCartItemViewModel().cartItems.observe(viewLifecycleOwner){
                val cartItem = it as ArrayList<CartItem>
                cartAdapter.addCartItem(cartItem)
                cartAdapter.notifyDataSetChanged()
                 btnPlaceOrder.visibility = View.VISIBLE
                if(cartItem.isEmpty()){
                    emptyList.visibility = View.VISIBLE
                    btnPlaceOrder.visibility = View.GONE
                }
        }
        icon_deletecard.setOnClickListener {
            SweetAlertDialog(view.context, SweetAlertDialog.WARNING_TYPE)
                .setTitleText("Xoá giỏ hàng")
                .setContentText("Bạn muốn xoá toàn bộ giỏ hàng?")
                .setConfirmText("Đồng ý")
                .setConfirmClickListener {
                    val sharedPreferencesUser = view.context.getSharedPreferences("user", AppCompatActivity.MODE_PRIVATE)
                    val userID = sharedPreferencesUser.getString("userID", null)
                    if(userID!=null)
                    {
                        appModel.removeAllCart(userID.toInt())
                    }

                    it.dismissWithAnimation()
                    (view.context as FragmentActivity).supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.flFragment, Cart(),"Cart").addToBackStack(null)
                        .commit()
                    val dataTest= view.context.getSharedPreferences("cart",AppCompatActivity.MODE_PRIVATE).edit().remove("productID").apply()
                    btnPlaceOrder.visibility =View.GONE
                    emptyList.visibility =View.VISIBLE
                }
                .setCancelText("Huỷ").setCancelClickListener {
                    it.dismissWithAnimation()
                }
                .show()

        }
        btnPlaceOrder.setOnClickListener {

            (view.context as FragmentActivity).supportFragmentManager
                .beginTransaction()
                .add(R.id.flFragment, Checkout(),"checkout").addToBackStack(null)
                .commit()

        }
        btnback.setOnClickListener {

            (view.context as FragmentActivity).supportFragmentManager
                .beginTransaction()
                .replace(R.id.flFragment, Homepage(),"Homepage").addToBackStack(null)
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

        SweetAlertDialog(view.context, SweetAlertDialog.WARNING_TYPE)
            .setTitleText("Thông báo từ giỏ hàng")
            .setContentText("Bạn muốn xoá sản phẩm này khỏi giỏ hàng?")
            .setConfirmText("Đồng ý")
            .setConfirmClickListener {
                Toast.makeText(
                    context, "Delete successfully",
                    Toast.LENGTH_SHORT).show()

                cartAdapter.deleteItem(position);
                appModel.removeItemCart(cartItem.getId())
                val sharedPreferences = view.context.getSharedPreferences("cart", AppCompatActivity.MODE_PRIVATE)
                val gson = Gson()
                val type: Type = object : TypeToken<ArrayList<Int>>() {}.type
                val carts=sharedPreferences.getString("productID", null)
                var dataItem = gson.fromJson<ArrayList<Int>>(carts, type);

                if (dataItem == null) {
                    dataItem = ArrayList<Int>()
                }
                dataItem.removeIf {
                    it==cartItem.getProductId()
                };
                sharedPreferences.edit().putString("productID",dataItem.toString()).apply()
                if(cartAdapter.itemCount==0){
                    emptyList.visibility = view.visibility
                    btnPlaceOrder.visibility = View.GONE
                }
                it.dismissWithAnimation()
            }.setCancelText("Huỷ").setCancelClickListener {
                it.dismissWithAnimation()
            }
            .show()




    }

    override fun onCartItemClickUpdate(position: Int, cartItem: CartItem) {

        appModel.getProductViewModel().getProduct(cartItem.getProductId())
        appModel.getProductViewModel().product.observe(this) {
            val product = it;
            productCartViewModel.setCartItemID(cartItem.getID())
            productCartViewModel.setId(product.getId())
            productCartViewModel.setName(product.getName())
            productCartViewModel.setImage(product.getImage())
            productCartViewModel.setDescription(product.getDescription())
            productCartViewModel.setPriceL(product.getPrice_L().toDouble())
            productCartViewModel.setPriceM(product.getPrice_M().toDouble())
            productCartViewModel.setPriceS(product.getPrice_S().toDouble())
            productCartViewModel.setCategoryId(product.getCategory_id())
            productCartViewModel.setTopping(cartItem.getTopping())
            productCartViewModel.setSize(cartItem.getSize())
            productCartViewModel.setPrice(cartItem.getPrice())
            productCartViewModel.setQuantiTy(cartItem.getQuantity())
            productCartViewModel.setNameFragment("cart");
            productCartViewModel.setNote(cartItem.getNotes());
            (view.context as FragmentActivity).supportFragmentManager
                .beginTransaction()
                .replace(R.id.flFragment, ProductDetail()).addToBackStack(null)
                .commit()
        }

        }





}
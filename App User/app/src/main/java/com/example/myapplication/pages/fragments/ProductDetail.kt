package com.example.myapplication.pages.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.bumptech.glide.Glide
import com.example.myapplication.MainActivity
import com.example.myapplication.R
import com.example.myapplication.viewmodels.AppViewModel


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ProductDetail.newInstance] factory method to
 * create an instance of this fragment.
 */
class ProductDetail : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private var productDetailImage : ImageView? = null
    private var productDetailName : TextView? = null
    private var productDetailPrice  : TextView? = null
    private var productDetailDescription : TextView? = null
    private val appModel: AppViewModel by activityViewModels()


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

        val view=inflater.inflate(R.layout.fragment_product_detail, container, false)
        initUI(view);

        (activity as MainActivity).showToolbarAndNavigationBar(false);
        val bundle = this.arguments
        var productDetailName  =  bundle?.getString("name").toString()
        var productDetailPrice =  bundle?.getString("price").toString()
        var productDetailDescription = bundle?.getString("description").toString()
        var productDetailImage = bundle?.getString("image").toString()
        setProductDetail(productDetailName, productDetailPrice, productDetailDescription, productDetailImage)
        return view;
    }
    private fun initUI(view:View){
        productDetailImage = view.findViewById(R.id.productDetailImageIV)
        productDetailName = view.findViewById(R.id.productDetailNameTV)
        productDetailPrice = view.findViewById(R.id.productDetailPriceTV)
        productDetailDescription = view.findViewById((R.id.productDetailDescriptionTV))
    }
    private fun setProductDetail(productName: String, productPrice: String, productDescription: String, productImage: String){
        productDetailName!!.text = productName
        productDetailPrice!!.text = productPrice
        productDetailDescription!!.text = productDescription
        Glide.with(this)
            .load(productImage)
            .into(productDetailImage!!)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ProductDetail.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ProductDetail().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
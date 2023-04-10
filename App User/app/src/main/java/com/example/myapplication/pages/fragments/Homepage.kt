package com.example.myapplication.pages.fragments

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.modals.Category
import com.example.myapplication.modals.Product
import com.example.myapplication.modals.Promotion
import com.example.myapplication.pages.apdaters.CategoryApdapter
import com.example.myapplication.pages.apdaters.GridSpacingItemDecoration
import com.example.myapplication.pages.apdaters.ProductApdapter
import com.example.myapplication.pages.apdaters.PromotionApdapter
import com.example.myapplication.utils.Utils
import com.example.myapplication.viewmodels.CategoryViewModel
import com.example.myapplication.viewmodels.ProductViewModel
import com.example.myapplication.viewmodels.PromotionViewModel
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType
import com.smarteist.autoimageslider.SliderAnimations
import com.smarteist.autoimageslider.SliderView

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Homepage.newInstance] factory method to
 * create an instance of this fragment.
 */
class Homepage : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var layoutManager: RecyclerView.LayoutManager
    private lateinit var recyclerViewProduct: RecyclerView
    private lateinit var recyclerViewCategory: RecyclerView
    private lateinit var sliderViewPromotion: SliderView
    private lateinit var categoryAdapter: CategoryApdapter
    private lateinit var productAdapter: ProductApdapter
    private lateinit var promotionAdapter: PromotionApdapter
    private lateinit var categoryViewModel: CategoryViewModel
    private lateinit var promotionViewModel: PromotionViewModel
    private lateinit var productViewModel: ProductViewModel

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }




    }
        private fun initUI(view: View) {
        var screenWidth = context?.let { Utils.getScreenWidth(it) }
        //Product
        recyclerViewProduct = view.findViewById(R.id.showProduct)
        productAdapter = ProductApdapter(this, arrayListOf())
            if (screenWidth != null) {
                recyclerViewProduct.addItemDecoration(
                    GridSpacingItemDecoration(
                        2, (screenWidth / 28.8).toInt(), true
                    )
                )
            }
        layoutManager = GridLayoutManager(context, 2)
        recyclerViewProduct.layoutManager = layoutManager
        recyclerViewProduct.adapter = productAdapter
        var screenHeight = context?.let { Utils.getScreenHeight(it) }
        if (screenWidth!! <= 1440) {
            recyclerViewProduct.addItemDecoration(
                GridSpacingItemDecoration(
                    2, (screenWidth / 28.8).toInt(), true
                )
            )
            layoutManager = GridLayoutManager(context, 2)
            recyclerViewProduct.layoutManager = layoutManager
            recyclerViewProduct.adapter = productAdapter
        } else {
            recyclerViewProduct.addItemDecoration(
                GridSpacingItemDecoration(
                    3, (screenWidth / 28.8).toInt(), true
                )
            )
            layoutManager = GridLayoutManager(context, 3)
            recyclerViewProduct.layoutManager = layoutManager
            recyclerViewProduct.adapter = productAdapter
        }


        //Category
        recyclerViewCategory = view.findViewById(R.id.showCategories)
        recyclerViewCategory.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        categoryAdapter = CategoryApdapter(this, arrayListOf())
        recyclerViewCategory.adapter = categoryAdapter

        //Promotion
        sliderViewPromotion = view.findViewById<SliderView>(R.id.imageSlider)!!
        promotionAdapter = PromotionApdapter(this, arrayListOf())
        sliderViewPromotion.setSliderAdapter(promotionAdapter)
        sliderViewPromotion.setIndicatorAnimation(IndicatorAnimationType.WORM) //set indicator animation by using IndicatorAnimationType. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        sliderViewPromotion.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION)
        sliderViewPromotion.autoCycleDirection = SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH
        sliderViewPromotion.indicatorSelectedColor = Color.WHITE
        sliderViewPromotion.indicatorUnselectedColor = Color.GRAY
        sliderViewPromotion.scrollTimeInSec = 4

    }

    private fun setUpViewModel(view: View) {
        categoryViewModel = ViewModelProvider(this)[CategoryViewModel::class.java]
        categoryViewModel.getCategories();
        productViewModel = ViewModelProvider(this)[ProductViewModel::class.java]
        productViewModel.getProducts();
        promotionViewModel = ViewModelProvider(this)[PromotionViewModel::class.java]
        promotionViewModel.getPromotions();
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun setUpObserver(view: View) {
        //Category
        categoryViewModel.categories.observe(viewLifecycleOwner) {
            val categories = it as ArrayList<Category>
            if (categories.isEmpty()) {
                categoryAdapter = CategoryApdapter(this, ArrayList<Category>())
                recyclerViewCategory.adapter = categoryAdapter
            } else {
                categoryAdapter.apply {
                    addCategory(categories)
                    notifyDataSetChanged();

                }
            }
        }

        //Product
        productViewModel.products.observe(viewLifecycleOwner) {
            val products = it as ArrayList<Product>
            if (products.isEmpty()) {
                productAdapter = ProductApdapter(this, ArrayList<Product>())
                recyclerViewCategory.adapter = categoryAdapter
            } else {
                productAdapter.apply {
                    addProducts(products)
                    notifyDataSetChanged();

                }
            }

        }

        //Promotion
        promotionViewModel.promotions.observe(viewLifecycleOwner) {
            val promotions = it as ArrayList<Promotion>
            if (promotions.isEmpty()) {
                promotionAdapter = PromotionApdapter(this, ArrayList<Promotion>())
                sliderViewPromotion.setSliderAdapter(promotionAdapter)
            } else {
                promotionAdapter.apply {
                    addPromotions(promotions)

                    notifyDataSetChanged();

                }
            }

        }

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_homepage, container, false);
        initUI(view); // Khởi tạo UI , cung cấp data rỗng cho apdapter để render --> Chưa có dữ liệu
        setUpViewModel(view) // call API
        setUpObserver(view); // Quan sát kết quả trả về từ API rồi gán giá trị cho apdapter
        return view;
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Homepage.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Homepage().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

}
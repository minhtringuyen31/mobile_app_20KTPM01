package com.example.myapplication.pages.fragments

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.andremion.counterfab.CounterFab
import com.example.myapplication.MainActivity
import com.example.myapplication.R
import com.example.myapplication.modals.CartItem
import com.example.myapplication.modals.Category
import com.example.myapplication.modals.Product
import com.example.myapplication.modals.Promotion
import com.example.myapplication.pages.activities.promotion.DetailPromotion
import com.example.myapplication.pages.apdaters.CategoryApdapter
import com.example.myapplication.pages.apdaters.GridSpacingItemDecoration
import com.example.myapplication.pages.apdaters.ProductApdapter
import com.example.myapplication.pages.apdaters.PromotionApdapter
import com.example.myapplication.pages.apdaters.interfaces.OnItemClickListener
import com.example.myapplication.pages.apdaters.interfaces.OnItemClickProductHomepage
import com.example.myapplication.pages.apdaters.interfaces.OnItemClickPromotion
import com.example.myapplication.utils.Utils
import com.example.myapplication.viewmodels.*
import com.example.myapplication.viewmodels.sharedata.ProductCartViewModel
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
class Homepage : Fragment(), OnItemClickListener, OnItemClickProductHomepage,OnItemClickPromotion {
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
    private lateinit var counterFab :CounterFab
    private lateinit var view:View
    private lateinit var progressBar: ProgressBar
    private val appModel: AppViewModel by activityViewModels()
    private val productCartViewModel: ProductCartViewModel by activityViewModels()

    @SuppressLint("NotifyDataSetChanged")
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
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_homepage, container, false)
        setUpViewModel()
        initUI(view) // Khởi tạo UI , cung cấp data rỗng cho apdapter để render --> Chưa có dữ liệu
        setUpObserver(view) // Quan sát kết quả trả về từ API rồi gán giá trị cho apdapter
        return view
    }   
    private  fun setUpViewModel(){
        (activity as MainActivity).showToolbarAndNavigationBar(true)
        appModel.setUpViewModel(view,this)
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
    private fun initUI(view: View) {
        progressBar = view.findViewById(R.id.showLoading)
        val screenWidth = context?.let { Utils.getScreenWidth(it) }
        //Product
        recyclerViewProduct = view.findViewById(R.id.showProduct)
        productAdapter = ProductApdapter(this, arrayListOf(),this,this)
        layoutManager = GridLayoutManager(context, 2)
        recyclerViewProduct.layoutManager = layoutManager
        recyclerViewProduct.adapter = productAdapter
        if (screenWidth!! <= 1440) {
            recyclerViewProduct.addItemDecoration(
                GridSpacingItemDecoration(
                    2, (screenWidth /10), true
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
        sliderViewPromotion = view.findViewById(R.id.imageSlider)!!
        promotionAdapter = PromotionApdapter(this, arrayListOf(),this)
        sliderViewPromotion.setSliderAdapter(promotionAdapter)
        sliderViewPromotion.setIndicatorAnimation(IndicatorAnimationType.WORM) //set indicator animation by using IndicatorAnimationType. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        sliderViewPromotion.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION)
        sliderViewPromotion.autoCycleDirection = SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH
        sliderViewPromotion.indicatorSelectedColor = Color.WHITE
        sliderViewPromotion.indicatorUnselectedColor = Color.GRAY
        sliderViewPromotion.scrollTimeInSec = 2
        counterFab = view.findViewById(R.id.fabTwo) as CounterFab

    }
    @SuppressLint("NotifyDataSetChanged", "SuspiciousIndentation")
    private fun setUpObserver(view: View) {
        //Category
       appModel.getCategoryViewModel().categories.observe(viewLifecycleOwner) {
            if(it!=null)
            {
                val categoryList = it as ArrayList<Category>
                categoryAdapter.addCategory(categoryList)
                categoryAdapter.notifyDataSetChanged()
                println("Loading category")
                progressBar.visibility = View.GONE
            }
           else
            {
                progressBar.visibility = View.VISIBLE
            }

       }
        //Product
        appModel.getProductViewModel().products.observe(viewLifecycleOwner) {
            if(it!=null)
            {
                val products = it as ArrayList<Product>
                productAdapter.addProducts(products)
                productAdapter.notifyDataSetChanged()
                println("Loading product")
                progressBar.visibility = View.GONE
            }
            else
            {
                progressBar.visibility = View.VISIBLE
            }

        }

        //Promotion
        appModel.getPromotionViewMode().promotions.observe(viewLifecycleOwner) {
            if(it!=null)
            {
                val promotions = it as ArrayList<Promotion>
                promotionAdapter.addPromotions(promotions)
                promotionAdapter.notifyDataSetChanged()
                println("Loading promotion")
                progressBar.visibility = View.GONE
            }
            else
            {
                progressBar.visibility = View.VISIBLE
            }

        }
        appModel.getCartItemViewModel().cartItems.observe(viewLifecycleOwner){
            counterFab.count= it.size
            println("Loading cart")
        }

        counterFab.setOnClickListener {
            (view.context as FragmentActivity).supportFragmentManager
                .beginTransaction()
                .replace(R.id.flFragment, Cart()).addToBackStack(null)
                .commit()
        }

    }

    override fun onItemClick(position: Int, product: Product) {
//      appModel.addtoCart(product)
        // on below line we are creating a new bottom sheet dialog.
        val bundle = Bundle()
        bundle.putString("id",product.getId().toString())
        bundle.putString("name",product.getName())
        bundle.putString("image", product.getImage())
        bundle.putString("price_S", product.getPrice_S().toString())
        bundle.putString("price_M", product.getPrice_M().toString())
        bundle.putString("price_L", product.getPrice_L().toString())
        bundle.putString("description",product.getDescription())
        bundle.putString("category_id",product.getCategory_id().toString())
        val bottomSheetDialogFragment = BottomSheetCartItem()
        bottomSheetDialogFragment.arguments=bundle
        bottomSheetDialogFragment.show((activity as FragmentActivity).supportFragmentManager, bottomSheetDialogFragment.tag)


    }

    override fun onCartItemClick(position: Int, cartItem: CartItem) {

    }

    override fun onCartItemClickUpdate(position: Int, cartItem: CartItem) {
        TODO("Not yet implemented")
    }

    override fun onItemClickHompage(position: Int, product: Product) {
            productCartViewModel.setId(product.getId())
            productCartViewModel.setName(product.getName())
            productCartViewModel.setImage(product.getImage())
            productCartViewModel.setDescription(product.getDescription())
            productCartViewModel.setPriceL(product.getPrice_L().toDouble())
            productCartViewModel.setPriceM(product.getPrice_M().toDouble())
            productCartViewModel.setPriceS(product.getPrice_S().toDouble())
            productCartViewModel.setCategoryId(product.getCategory_id())
            productCartViewModel.setQuantiTy(1)
            productCartViewModel.setTopping("");
            productCartViewModel.setNameFragment("homepage")
            productCartViewModel.setNote("");
        (view.context as FragmentActivity).supportFragmentManager
                .beginTransaction()
                .replace(R.id.flFragment, ProductDetail()).addToBackStack(null)
                .commit()
    }

    override fun onItemClickDetailPromotion(position: Int, promotion: Promotion) {
        val intent = Intent(context, DetailPromotion::class.java)
        intent.putExtra("promotion", promotion as Parcelable)
        startActivity(intent)
    }


}
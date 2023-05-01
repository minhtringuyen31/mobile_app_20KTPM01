package com.example.myapplication.pages.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.andremion.counterfab.CounterFab
import com.bumptech.glide.Glide
import com.example.myapplication.MainActivity
import com.example.myapplication.R
import com.example.myapplication.modals.*
import com.example.myapplication.pages.apdaters.RatingListAdapter
import com.example.myapplication.utils.Utils
import com.example.myapplication.viewmodels.*
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
 * Use the [ProductDetail.newInstance] factory method to
 * create an instance of this fragment.
 */
class ProductDetail : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var item= arrayOf(1.0, 0.0, 1.0)
    private lateinit var productDetailImage : ImageView
    private lateinit var productDetailName : TextView
    private lateinit var productDetailPrice  : TextView
    private lateinit var productDetailDescription : TextView
    private lateinit var backHomepage:ImageButton
    private lateinit var iconFaverite:ImageButton
    private lateinit var btnAddtoCart:Button
    private lateinit var toppingApdapter: BottomSheetCartItem.ToppingApdapter
    private lateinit var toppingListView: ListView
    private lateinit var text_quantity:TextView
    private lateinit var priceS_radio:RadioButton
    private lateinit var priceM_radio:RadioButton
    private lateinit var plusBtn:Button
    private var category_id: Int = 0
    private lateinit var minusBtn:Button
    private lateinit var priceL_radio:RadioButton
    private lateinit var itemCount: CountItemInBottomSheet
    private lateinit var priceL:TextView
    private lateinit var priceS:TextView
    private lateinit var priceM:TextView
    private  var product_id:Int =0
    private lateinit var name:String
    private lateinit var image:String
    private lateinit var description:String
    private  var priceL_text:Double=0.0
    private  var priceM_text:Double=0.0
    private  var priceS_text:Double=0.0
    private val productCartViewModel: ProductCartViewModel by activityViewModels()

    private lateinit var ratingRecyclerView: RecyclerView
//    private lateinit var ratingViewModel: RatingViewModel
    private lateinit var ratingListAdapter: RatingListAdapter

    private val appModel: AppViewModel by activityViewModels()
    private lateinit var topping:String
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
        setUpViewModel()
        initUI(view)
        topping= productCartViewModel.getTopping()
        setUpObserve(view)

        return view
    }
    private  fun setUpViewModel(){

        appModel.setUpRatingViewMode(this, productCartViewModel.getId())
        println( productCartViewModel.getId())
        appModel.setUpToppingViewModel(this)
        itemCount= ViewModelProvider(this)[CountItemInBottomSheet::class.java]
    }
    @SuppressLint("SetTextI18n")
    private fun initUI(view:View){
        (activity as MainActivity).showToolbarAndNavigationBar(false)
        plusBtn = view.findViewById(R.id.plus_btn)
        minusBtn=view.findViewById(R.id.minus_btn)
        productDetailImage = view.findViewById(R.id.productDetailImageIV)
        productDetailName = view.findViewById(R.id.productDetailNameTV)
        productDetailPrice = view.findViewById(R.id.productDetailPriceTV)
        productDetailDescription = view.findViewById((R.id.productDetailDescriptionTV))
        backHomepage = view.findViewById(R.id.imgToolbarBtnBack)
        iconFaverite= view.findViewById(R.id.imgToolbarBtnFav)
        text_quantity = view.findViewById(R.id.text_quantity)
        priceS_radio = view.findViewById(R.id.priceS_radio)
        priceM_radio = view.findViewById(R.id.priceM_radio)
        priceL_radio = view.findViewById(R.id.priceL_radio)
        priceL = view.findViewById(R.id.priceL)
        priceM = view.findViewById(R.id.priceM)
        priceS = view.findViewById(R.id.priceS)
        btnAddtoCart = view.findViewById(R.id.btnAddtoCart)
        toppingListView = view.findViewById<View>(R.id.checkboxlistView) as ListView
        toppingApdapter = BottomSheetCartItem.ToppingApdapter(arrayListOf(), view.context,productCartViewModel.getTopping())
        toppingListView.adapter = toppingApdapter
        toppingListView.isFocusable = false
        toppingListView.isFocusableInTouchMode = false
        name= productCartViewModel.getName()
        priceS_text=  productCartViewModel.getPriceS()
        description  = productCartViewModel.getDescription()
        image = productCartViewModel.getImage()
        priceL_text=  productCartViewModel.getPriceL()
        priceM_text= productCartViewModel.getPriceM()
        category_id = productCartViewModel.getCategoryId()
        priceL.text= Utils.formatCurrency(  productCartViewModel.getPriceL()) + " đ"
        priceM.text = Utils.formatCurrency(  productCartViewModel.getPriceM())+ " đ"
        priceS.text = Utils.formatCurrency(  productCartViewModel.getPriceS())+ " đ"
        product_id=productCartViewModel.getId()
        setProductDetail(name,priceL_text.toString() , description, image)
        val quantity=  productCartViewModel.getQuantiTy()
        itemCount.count=quantity
        displayCount()
        if(productCartViewModel.getNameFragment()=="cart"){
           
            item[0]=productCartViewModel.getPrice()
        }
        else {
            item[0]=productCartViewModel.getPriceL()
        }
        itemCount.total=calculateTotalPrice()
        updatePriceTotal()
        ratingRecyclerView = view.findViewById(R.id.listRatingRV)
    }
    @SuppressLint("SetTextI18n")
    private fun updatePriceTotal() {
        btnAddtoCart.text = "Add to cart - " +  Utils.formatCurrency( itemCount.total) + " đ"
    }
    private fun displayCount() {
        text_quantity.text = itemCount.count.toString()
    }
    private fun calculateTotalPrice(): Double {
        return (item[0]+item[1])*item[2]
    }
    private fun setUpObserve(view:View){

        priceL_radio.setOnClickListener {
            priceL_radio.isChecked = true
            priceM_radio.isChecked = false
            priceS_radio.isChecked = false
            item[0]=Utils.getDigitInString(priceL.text.toString())
            itemCount.total = calculateTotalPrice()
            itemCount.size="L"
            updatePriceTotal()
        }
        priceM_radio.setOnClickListener {
            priceL_radio.isChecked = false
            priceM_radio.isChecked = true
            priceS_radio.isChecked = false
            item[0]=Utils.getDigitInString(priceM.text.toString())
            itemCount.total = calculateTotalPrice()
            itemCount.size="M"
            updatePriceTotal()
        }
        priceS_radio.setOnClickListener {
            priceL_radio.isChecked = false
            priceM_radio.isChecked = false
            priceS_radio.isChecked = true
            item[0]=Utils.getDigitInString(priceS.text.toString())
            println(Utils.getDigitInString(priceS.text.toString()))
            itemCount.total = calculateTotalPrice()
            itemCount.size="S"
            updatePriceTotal()
        }
        plusBtn.setOnClickListener {
            itemCount.count = itemCount.count + 1
            displayCount()
            item[2]= itemCount.count.toDouble()
            itemCount.total = calculateTotalPrice()
            updatePriceTotal()

        }
        minusBtn.setOnClickListener {
            itemCount.count = itemCount.count - 1
            if( itemCount.count<=1){
                itemCount.count =1 
            }
            displayCount()
            item[2]= itemCount.count.toDouble()
            itemCount.total = calculateTotalPrice()
            updatePriceTotal()
        }
        appModel.getToppingViewModel().toppings.observe(viewLifecycleOwner){
            val toppings=it
            if(toppings!=null){
                toppingApdapter.apply {
                    toppingApdapter.addToppings(toppings.filter { it-> it.getCategoryID() == category_id } as ArrayList<Topping>)
                    notifyDataSetChanged()
                }
            }

        }
        toppingListView.onItemClickListener =
            AdapterView.OnItemClickListener { adapterView, view, position, id -> // Handle item click here
                val checkboxTopping = adapterView.getItemAtPosition(position) as Topping
                checkboxTopping.setChecked(if (checkboxTopping.getChecked()==1) 0 else 1)
                if(checkboxTopping.getChecked()==1) {
                    adapterView[position].findViewById<CheckBox>(R.id.checkbox).isChecked=true
                    item[1]+=Utils.getDigitInString(checkboxTopping.getPrice().toInt().toString())
                    itemCount.nameTopping=  itemCount.nameTopping+"-"+checkboxTopping.getName()
                } else {
                    adapterView[position].findViewById<CheckBox>(R.id.checkbox).isChecked=false
                    var checkNegative= item[1]
                    checkNegative-=Utils.getDigitInString(checkboxTopping.getPrice().toInt().toString())
                    if(item[1]<=0) {item[1] =0.0 } else { item[1]=checkNegative }
                    itemCount.nameTopping=""
                }
                itemCount.total=calculateTotalPrice()
                updatePriceTotal()
            }
        btnAddtoCart.setOnClickListener {

            val sharedPreferences = view.context.getSharedPreferences("cart", AppCompatActivity.MODE_PRIVATE)

            val gson = Gson()
            val type: Type = object : TypeToken<ArrayList<Int>>() {}.type
            val carts=sharedPreferences.getString("productID", null)
            var dataItem = gson.fromJson<ArrayList<Int>>(carts, type);

            if (dataItem == null) {
                dataItem = ArrayList<Int>()
            }
            if(dataItem.contains(product_id)&&dataItem.isNotEmpty()){
                Toast.makeText(
                    activity, "Sản phẩm đã tồn tại trong giỏ hàng",
                    Toast.LENGTH_SHORT
                ).show()
            }
            else{
                val sharedPreferencesUser = view.context.getSharedPreferences("user", AppCompatActivity.MODE_PRIVATE)
                val userID = sharedPreferencesUser.getString("userID", "")
                val cartItem = CartItem(
                    userID!!.toInt(),
                    userID!!.toInt(),
                    product_id,
                    itemCount.count,
                    itemCount.size,
                    itemCount.total,
                    itemCount.nameTopping,
                    name,
                    description,
                    image,
                    category_id
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
                    .replace(R.id.flFragment, Order(),"Order").addToBackStack(null)
                    .commit()
                val tempData = carts;
                dataItem.add(product_id);
                sharedPreferences.edit().putString("productID",dataItem.toString()).apply()
            }


        }
        appModel.getRatingViewModel().ratings.observe(viewLifecycleOwner){
            val ratings = it as ArrayList<Rating>
            if (ratings.isEmpty()) {
                setUpRatingRecyclerAdapter(view, arrayListOf())

            } else {
                setUpRatingRecyclerAdapter(view, ratings)
            }
        }
        backHomepage.setOnClickListener {
            if(productCartViewModel.getNameFragment()=="cart"){
                (view.context as FragmentActivity).supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.flFragment, Cart()).addToBackStack(null)
                    .commit()
            }
            else {
                (view.context as FragmentActivity).supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.flFragment, Homepage()).addToBackStack(null)
                    .commit()
            }
        }
    }

    private fun setUpRatingRecyclerAdapter(view: View, data: ArrayList<Rating>) {
        ratingListAdapter = RatingListAdapter(data)
        ratingRecyclerView.adapter = ratingListAdapter
        ratingRecyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        ratingListAdapter.onItemClick = {

        }
    }
    private fun setProductDetail(productName: String, productPrice: String, productDescription: String, productImage: String){
        productDetailName.text = productName
        productDetailPrice.text = productPrice
        productDetailDescription.text = productDescription
        Glide.with(this)
            .load(productImage)
            .into(productDetailImage)
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
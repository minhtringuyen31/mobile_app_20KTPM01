package com.example.myapplication.pages.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import com.andremion.counterfab.CounterFab
import com.bumptech.glide.Glide
import com.example.myapplication.MainActivity
import com.example.myapplication.R
import com.example.myapplication.modals.CartItem
import com.example.myapplication.modals.Topping
import com.example.myapplication.viewmodels.AppViewModel
import com.example.myapplication.viewmodels.CountItemInBottomSheet
import com.example.myapplication.viewmodels.ProductCartViewModel


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
    private var item= arrayOf(1.0, 1.0, 1.0)
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
    private var category_id= 0
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
        appModel.setUpToppingViewModel(this)

        itemCount= ViewModelProvider(this)[CountItemInBottomSheet::class.java]
    }
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
        toppingApdapter = BottomSheetCartItem.ToppingApdapter(arrayListOf(), view.context)
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
        priceL.text=priceL_text.toString()
        priceM.text=priceM_text.toString()
        priceS.text=priceS_text.toString()
        product_id=productCartViewModel.getId()
        setProductDetail(name,priceL_text.toString() , description, image)


    }
    private fun updatePriceTotal() {
        btnAddtoCart.text = "Add to cart - " + itemCount.total.toString() + " đ"
    }
    private fun displayCount() {
        text_quantity.text = itemCount.count.toString()
    }
    private fun calculateTotalPrice(): Double {
        return (item[0]+item[1])*item[2]
    }

    private fun setUpObserve(view:View){


        backHomepage.setOnClickListener {
            getFragmentManager()?.popBackStack()
        }
        iconFaverite.setOnClickListener {
            println("Favorite icon")
        }
        priceL_radio.setOnClickListener {
            priceL_radio.isChecked = true
            priceM_radio.isChecked = false
            priceS_radio.isChecked = false
            item[0]=priceL.text.toString().toDouble()
            itemCount.total = calculateTotalPrice()
            itemCount.size="L"
            updatePriceTotal()
        }

        priceM_radio.setOnClickListener {
            priceL_radio.isChecked = false
            priceM_radio.isChecked = true
            priceL_radio.isChecked = false
            item[0]=priceM.text.toString().toDouble()
            itemCount.total = calculateTotalPrice()
            itemCount.size="M"
            updatePriceTotal()

        }

        priceS_radio.setOnClickListener {
            priceL_radio.isChecked = false
            priceM_radio.isChecked = false
            priceS_radio.isChecked = true
            item[0]=priceS.text.toString().toDouble()
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
                    toppingApdapter.setSelectedTopping(topping);

                    notifyDataSetChanged()

                }
            }

        }

        toppingListView.onItemClickListener =
            AdapterView.OnItemClickListener { adapterView, view, position, id -> // Handle item click here
                val checkboxTopping = adapterView.getItemAtPosition(position) as Topping
                checkboxTopping.setChecked(if (checkboxTopping.getChecked()==1) 0 else 1)
                if(checkboxTopping.getChecked()==1) {
                    adapterView.findViewById<CheckBox>(R.id.checkbox).isChecked=true
                    item[1]+=checkboxTopping.getPrice()
                    itemCount.nameTopping=  itemCount.nameTopping+"-"+checkboxTopping.getName()
                    checkboxTopping.setChecked(1)
                    itemCount.total=calculateTotalPrice()
                    updatePriceTotal()
                } else {
                    adapterView.findViewById<CheckBox>(R.id.checkbox).isChecked=false
                    item[1]=0.0
                    itemCount.nameTopping="EMPTY"
                    itemCount.total=calculateTotalPrice()
                    updatePriceTotal()
                    checkboxTopping.setChecked(0)
                }
            }


        btnAddtoCart.setOnClickListener {
            // response from server when execute authentication include {user_id,email,phone,cart_id}
            // and then store above information into share preference in android
            val cartItem = CartItem(
                3,
                1,
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
                activity, "Add to cart success",
                Toast.LENGTH_LONG
            ).show()
            val myFragment = parentFragmentManager.findFragmentByTag("Homepage") as Homepage?
            myFragment?.view?.findViewById<CounterFab>(R.id.fabTwo)?.increase()

            (view.context as FragmentActivity).supportFragmentManager
                .beginTransaction()
                .replace(R.id.flFragment, Homepage()).addToBackStack(null)
                .commit()

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
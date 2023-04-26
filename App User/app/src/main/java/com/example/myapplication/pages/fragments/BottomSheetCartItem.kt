package com.example.myapplication.pages.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import com.andremion.counterfab.CounterFab
import com.example.myapplication.R
import com.example.myapplication.modals.CartItem
import com.example.myapplication.modals.Topping
import com.example.myapplication.viewmodels.AppViewModel
import com.example.myapplication.viewmodels.CountItemInBottomSheet
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [BottomSheetCartItem.newInstance] factory method to
 * create an instance of this fragment.
 */
    class BottomSheetCartItem : BottomSheetDialogFragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private var item= arrayOf(1.0, 0.0, 1.0)
    private lateinit var toppingApdapter:ToppingApdapter
    private lateinit var  toppingListView:ListView
    private val appModel: AppViewModel by activityViewModels()
    private lateinit var plusBtn:Button
    private lateinit var minusBtn:Button
    private lateinit var priceSRadio:RadioButton
    private lateinit var priceMRadio:RadioButton
    private lateinit var priceL_radio:RadioButton
    private lateinit var itemCount: CountItemInBottomSheet
    private lateinit var text_quantity:TextView
    private lateinit var priceL:TextView
    private lateinit var priceS:TextView
    private lateinit var priceM:TextView
    private lateinit var nameBottom :TextView
    private lateinit var btnAddtoCart:Button
    private var category_id= 0
    private var product_id:Int =0
    private lateinit var name:String
    private lateinit var image:String
    private lateinit var description:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
        setStyle(STYLE_NORMAL, R.style.CustomBottomSheetDialogTheme)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view= inflater.inflate(R.layout.fragment_bottom_sheet_cart_item, container, false)
        // Inflate the layout for this fragment


        setUpViewModel()
        initUI(view)
        setUpObserve(view)


        return view
    }
    private fun setUpViewModel(){
        appModel.setUpToppingViewModel(this)
    }
    private fun displayCount() {
        text_quantity.text = itemCount.count.toString()
    }
    private fun updatePriceTotal() {
        btnAddtoCart.text = "Add to cart - " + itemCount.total + "đ"
    }
    private fun initUI(view:View){
        // Initializing the elements from the main layout file
        itemCount= ViewModelProvider(this)[CountItemInBottomSheet::class.java]
        plusBtn = view.findViewById(R.id.plus_btn)
        minusBtn=view.findViewById(R.id.minus_btn)
        toppingListView = view.findViewById<View>(R.id.checkboxlistView) as ListView
        toppingApdapter = ToppingApdapter(arrayListOf(), view.context)
        toppingListView.adapter = toppingApdapter
        text_quantity = view.findViewById(R.id.text_quantity)
        priceSRadio = view.findViewById(R.id.priceS_radio)
        priceMRadio = view.findViewById(R.id.priceM_radio)
        priceL_radio = view.findViewById(R.id.priceL_radio)
        priceL = view.findViewById(R.id.priceL)
        priceM = view.findViewById(R.id.priceM)
        priceS = view.findViewById(R.id.priceS)
        btnAddtoCart = view.findViewById(R.id.btnPrice)
        nameBottom = view.findViewById(R.id.name_product_bottom)

        arguments?.let {
            product_id = it.getString("id")?.toInt()!!
            priceL.text= it.getString("price_L")
            priceM.text = it.getString("price_M")
            priceS.text = it.getString("price_S")
            name = it.getString("name")!!
            image= it.getString("image")!!
            category_id = it.getString("category_id")!!.toInt()
            description = it.getString("description")!!
            itemCount.total= priceL.text.toString().toDouble()
            updatePriceTotal()
            nameBottom.text = name
        }
        toppingListView.isFocusable = false
        toppingListView.isFocusableInTouchMode = false


    }

    private fun setUpObserve(view:View){

        priceL_radio.setOnClickListener {
            priceL_radio.isChecked = true
            priceMRadio.isChecked = false
            priceSRadio.isChecked = false
            item[0]=priceL.text.toString().toDouble()
            itemCount.total = calculateTotalPrice()
            itemCount.size="L"
            updatePriceTotal()
        }

        priceMRadio.setOnClickListener {
            priceL_radio.isChecked = false
            priceMRadio.isChecked = true
            priceL_radio.isChecked = false
            item[0]=priceM.text.toString().toDouble()
            itemCount.total = calculateTotalPrice()
            itemCount.size="M"
            updatePriceTotal()

        }

        priceSRadio.setOnClickListener {
            priceL_radio.isChecked = false
            priceMRadio.isChecked = false
            priceSRadio.isChecked = true
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
                    itemCount.nameTopping=""
                    itemCount.total=calculateTotalPrice()
                    updatePriceTotal()
                    checkboxTopping.setChecked(0)
                }
            }

        btnAddtoCart.setOnClickListener {
            // response from server when execute authentication include {user_id,email,phone,cart_id}
            //and then store above information into share preference in android
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
            dismiss()
            val myFragment = parentFragmentManager.findFragmentByTag("Homepage") as Homepage?
            myFragment?.view?.findViewById<CounterFab>(R.id.fabTwo)?.increase()
        }


    }
    private fun calculateTotalPrice(): Double {
        return (item[0]+item[1])*item[2]
    }

    class ToppingApdapter(private val toppings: ArrayList<Topping>, mContext: Context) :
        ArrayAdapter<Any?>(mContext, R.layout.item_topping, toppings as ArrayList<*>) {
        private class ViewHolder {
            lateinit var checkBox: CheckBox
            lateinit var txtName: TextView
            lateinit var txtPrice: TextView

        }

        override fun getCount(): Int {
            return toppings.size
        }

        override fun getItem(position: Int): Topping {
            return toppings[position] as Topping
        }

        override fun getView(
            position: Int,
            convertView: View?,
            parent: ViewGroup
        ): View {
            var convertView = convertView
            val viewHolder: ViewHolder
            val result: View
            if (convertView == null) {
                viewHolder = ViewHolder()
                convertView =
                    LayoutInflater.from(parent.context).inflate(R.layout.item_topping, parent, false)
                viewHolder.txtName =
                    convertView.findViewById(R.id.nameTopping)
                viewHolder.checkBox =
                    convertView.findViewById(R.id.checkbox)
                viewHolder.txtPrice =
                    convertView.findViewById(R.id.priceTopping)
                result = convertView
                convertView.tag = viewHolder
            } else {
                viewHolder = convertView.tag as ViewHolder
                result = convertView
            }

            val item: Topping = getItem(position)
            viewHolder.txtName.text = item.getName()
            viewHolder.txtPrice.text = item.getPrice().toString()
            viewHolder.checkBox.isChecked = item.getChecked() != (0 ?: false)
            return result
        }
        fun addToppings(promotions: ArrayList<Topping>) {
            this.toppings.apply {
                clear()
                addAll(promotions)
                notifyDataSetChanged()
            }
        }
        fun setSelectedTopping(name:String){
            for(item in toppings){
                println(item.getName())
                if( name.contains(item.getName())&&name.isNotEmpty()){
                    item.setChecked(1)
                    notifyDataSetChanged()
                }
            }
        }
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment BottomSheetCartItem.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            BottomSheetCartItem().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}

package com.example.myapplication.pages

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.Rect
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import com.example.myapplication.R
import com.example.myapplication.modals.Category
import com.example.myapplication.modals.Product
import com.example.myapplication.modals.Promotion
import com.example.myapplication.utils.Utils
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType
import com.smarteist.autoimageslider.SliderAnimations
import com.smarteist.autoimageslider.SliderView
import com.smarteist.autoimageslider.SliderViewAdapter
import java.io.ByteArrayOutputStream


class MyListProduct(private val context: Homepage, private val data: List<Product>) : RecyclerView.Adapter<MyListProduct.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageView = view.findViewById(R.id.image_product) as ImageView
        val name = view.findViewById(R.id.name_product) as TextView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_product, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.imageView.setImageBitmap(data[position].getDecodeImage())
        holder.name.text = data[position].getName();
        holder.itemView.setOnClickListener {
            val intent = Intent(
                context,
                ProductDetail::class.java
            )
            intent.putExtra("name", data[position].getName())
            intent.putExtra("image", data[position].getImage())
            intent.putExtra("price",data[position].getPrice())

            intent.putExtra("description", data[position].getDescription())
            context.startActivity(intent)
        }
    }
    override fun getItemCount(): Int {
        return data.size
    }

}
//class MyListPromotion(private val context: homepage, private val data: List<Promotion>)
//    : RecyclerView.Adapter<MyListPromotion.ViewHolder>() {
//
//    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
//        val imageView = view.findViewById(R.id.image_categories) as ImageView
//        val name = view.findViewById(R.id.name_categories) as TextView
//
//    }
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_categories, parent, false)
//        return ViewHolder(view)
//    }
//    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        holder.imageView.setImageBitmap(data[position].getDecodeImage())
//        holder.name.text = data[position].getName();
//        holder.itemView.setOnClickListener {
//            // xu li khi an vao cac categories
//        }
//    }
//    override fun getItemCount(): Int {
//        return data.size
//    }
//}

class MyListCategories(private val context: Homepage, private val data: List<Category>)
    : RecyclerView.Adapter<MyListCategories.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageView = view.findViewById(R.id.image_categories) as ImageView
        val name = view.findViewById(R.id.name_categories) as TextView

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_categories, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.imageView.setImageBitmap(data[position].getDecodeImage())
        holder.name.text = data[position].getName();
        holder.itemView.setOnClickListener {
            val intent = Intent(
                context,
                ProductList::class.java
            )
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }
}

class MySliderAdapter(private val images: List<Promotion>) :
    SliderViewAdapter<MySliderAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_promotion, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        viewHolder.imageView.setImageBitmap(images[position].getDecodeImage())
    }

    override fun getCount(): Int = images.size

    inner class ViewHolder(itemView: View) : SliderViewAdapter.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.mypromotion)
    }
}


class GridSpacingItemDecoration(
    private val spanCount: Int,
    private val spacing: Int,
    private val includeEdge: Boolean
) :
    ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val position = parent.getChildAdapterPosition(view) // item position
        val column = position % spanCount // item column
        if (includeEdge) {
            outRect.left =
                spacing - column * spacing / spanCount // spacing - column * ((1f / spanCount) * spacing)
            outRect.right =
                (column + 1) * spacing / spanCount // (column + 1) * ((1f / spanCount) * spacing)
            if (position < spanCount) { // top edge
                outRect.top = spacing
            }
            outRect.bottom = spacing // item bottom
        } else {
            outRect.left = column * spacing / spanCount // column * ((1f / spanCount) * spacing)
            outRect.right =
                spacing - (column + 1) * spacing / spanCount // spacing - (column + 1) * ((1f /    spanCount) * spacing)
            if (position >= spanCount) {
                outRect.top = spacing // item top
            }
        }
    }
}
class Homepage : AppCompatActivity() {

    private var recyclerViewStudents: RecyclerView? = null
    private var recyclerViewProduct: RecyclerView? = null
    private var recyclerViewPromotionBorder:RecyclerView ? =null
    private lateinit var layoutManager: RecyclerView.LayoutManager
    private lateinit var adapter: RecyclerView.Adapter<*>

     fun getProductList(): List<Product> {
        var productList=ArrayList<Product>();
        productList.add(Product("1","Capuchino","Đồ uống",50.0,"M",12.0,12.0,12.0,"Blabla","images/product/product01.png",true,"123","12/02/2002","12.",1))
        productList.add(Product("2","Trà Sen Vàng","Đồ uống",58.0,"M",12.0,12.0,12.0,"Blabla","images/product/product02.png",true,"123","12/02/2002","12.",1))
        productList.add(Product("3","Phin Sữa đá","Đồ uống",30.0,"M",12.0,12.0,12.0,"Blabla","images/product/product03.png",true,"123","12/02/2002","12.",1))
        productList.add(Product("4","Freeze Trà Xanh","Đồ uống",25.0,"M",12.0,12.0,12.0,"Blabla","images/product/product04.png",true,"123","12/02/2002","12.",1))
        productList.add(Product("5","Phidil Hạnh Nhân","Đồ uống",64.0,"M",12.0,12.0,12.0,"Blabla","images/product/product10.png",true,"123","12/02/2002","12.",1))
        productList.add(Product("6","Phildi Chôc","Đồ uống",56.0,"M",12.0,12.0,12.0,"Blabla","images/product/product14.png",true,"123","12/02/2002","12.",1))
        productList.add(Product("5","Phidi  l Hạnh Nhân","Đồ uống",64.0,"M",12.0,12.0,12.0,"Blabla","images/product/product15.png",true,"123","12/02/2002","12.",1))
        productList.add(Product("6","Phildi Chôc","Đồ uống",56.0,"M",12.0,12.0,12.0,"Blabla","images/product/product16.png",true,"123","12/02/2002","12.",1))
        productList.add(Product("5","Phidil Hạnh Nhân","Đồ uống",64.0,"M",12.0,12.0,12.0,"Blabla","images/product/product15.png",true,"123","12/02/2002","12.",1))
        productList.add(Product("6","Phildi Chôc","Đồ uống",56.0,"M",12.0,12.0,12.0,"Blabla","images/product/product20.png",true,"123","12/02/2002","12.",1))
        return productList;
    }
     fun getPromotionList(): List<Promotion> {
        var slider=ArrayList<Promotion>();
        slider.add(Promotion("#voucher01","Cà phê truyền thống","Ưu đãi đặc biệt",10.0,"images/promotion/voucher01.png","23/03/2023","31/03/2023"))
        slider.add(Promotion("#voucher02","Cà phê pha máy","Ưu đãi đặc biệt",12.0,"images/promotion/voucher02.jpeg","23/03/2023","31/03/2023"))
        slider.add(Promotion("#voucher03","Đá xay","Ưu đãi đặc biệt",13.0,"images/promotion/voucher03.jpeg","23/03/2023","31/03/2023"))
        slider.add(Promotion("#voucher01","Cà phê truyền thống","Ưu đãi đặc biệt",10.0,"images/promotion/voucher01.png","23/03/2023","31/03/2023"))
        slider.add(Promotion("#voucher02","Cà phê pha máy","Ưu đãi đặc biệt",12.0,"images/promotion/voucher02.jpeg","23/03/2023","31/03/2023"))
        slider.add(Promotion("#voucher03","Đá xay","Ưu đãi đặc biệt",13.0,"images/promotion/voucher03.jpeg","23/03/2023","31/03/2023"))
        slider.add(Promotion("#voucher01","Cà phê truyền thống","Ưu đãi đặc biệt",10.0,"images/promotion/voucher01.png","23/03/2023","31/03/2023"))
        slider.add(Promotion("#voucher02","Cà phê pha máy","Ưu đãi đặc biệt",12.0,"images/promotion/voucher02.jpeg","23/03/2023","31/03/2023"))
        slider.add(Promotion("#voucher03","Đá xay","Ưu đãi đặc biệt",13.0,"images/promotion/voucher03.jpeg","23/03/2023","31/03/2023"))
        return slider;
    }
     fun getCategoryList(): List<Category> {
        var data=ArrayList<Category>();
        data.add(Category("#caphett","Cà phê truyền thống","images/categories/Caphett.png"))
        data.add(Category("#caphemay","Cà phê pha máy","images/categories/Caphephamay.png"))
        data.add(Category("#daxay","Đá xay","images/categories/Daxay.png"))
        data.add(Category("#douongkhac","Đồ uống khác","images/categories/Douongkhac.png"))
        data.add(Category("#phidi","Phi di","images/categories/Phidi.png"))
        data.add(Category("#tra","Trà","images/categories/Tra.png"))
        return data;
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_homepage)

        val view = findViewById<View>(android.R.id.content)
        Utils.activeToolbar(this,view);
        Utils.activeNavigationBar(this,view);



        recyclerViewProduct = findViewById(R.id.showProduct)

        val myListAdapterProduct = MyListProduct(this, getProductList()!!)



        var screenWidth = Utils.getScreenWidth(this)

        var screenHeight = Utils.getScreenHeight(this)


        if(screenWidth<=1440)
        {
            recyclerViewProduct?.addItemDecoration(GridSpacingItemDecoration(2, (screenWidth/28.8).toInt(),true))
            layoutManager = GridLayoutManager(this,2)
            recyclerViewProduct?.layoutManager = layoutManager
            recyclerViewProduct?.adapter = myListAdapterProduct
        }
        else
        {
            recyclerViewProduct?.addItemDecoration(GridSpacingItemDecoration(3, (screenWidth/28.8).toInt(),true))
            layoutManager = GridLayoutManager(this,3)
            recyclerViewProduct?.layoutManager = layoutManager
            recyclerViewProduct?.adapter = myListAdapterProduct
        }
        recyclerViewStudents = findViewById(R.id.showCategories)

            var data = getCategoryList();
            val myListAdapter = MyListCategories(this, data!!)
            layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL, false)
            recyclerViewStudents?.layoutManager = layoutManager
            recyclerViewStudents?.adapter = myListAdapter


        var slider = getPromotionList();
        val sliderView = findViewById<SliderView>(R.id.imageSlider)

        val adapter = MySliderAdapter(slider)

        sliderView.setSliderAdapter(adapter)

        sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM) //set indicator animation by using IndicatorAnimationType. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!

        sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION)
        sliderView.autoCycleDirection =
            SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH
        sliderView.indicatorSelectedColor = Color.WHITE
        sliderView.indicatorUnselectedColor = Color.GRAY
        sliderView.scrollTimeInSec = 4










    }
}
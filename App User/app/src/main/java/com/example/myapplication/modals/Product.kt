package com.example.myapplication.modals



@kotlinx.serialization.Serializable
data class Product( private  var id: Int,
               private  var name: String,
               private  var description: String,
               private  var size: String,
               private var price_S:Int,
               private var price_M:Int,
               private var price_L: Int,
               private  var image: String,
               private var status: Int,
               private  var category_id: Int,
               private var update_date:String,
               private var release_date:String,
               private var sales: Int,
               private var is_disable: Int,
){



    fun getId(): Int {
        return id
    }

    fun getDisable(): Int {
        return is_disable
    }
    fun setDisable(status: Int) {
        this.is_disable = status
    }

    fun setId(id: Int) {
        this.id = id
    }

    fun getName(): String {
        return name
    }

    fun setName(name: String) {
        this.name = name
    }

    fun getDescription():String
    {
        return description;
    }
    fun setDescription(description:String)
    {
        this.description=description;
    }



    fun getSize():String
    {
        return size;
    }

    fun setSize(size:String)
    {
        this.size=size;
    }

    fun getPrice_S():Int
    {
        return price_S;
    }

    fun setPrice_S(price_S :Int)
    {
        this.price_S=price_S;
    }

    fun getPrice_M():Int
    {
        return price_M;
    }

    fun setPrice_M(price_M :Int)
    {
        this.price_M=price_M;
    }

    fun getPrice_L():Int
    {
        return price_L;
    }

    fun setPrice_L(price_L :Int)
    {
        this.price_L=price_L;
    }


    fun getImage():String
    {
        return image;
    }

    fun setImage(image:String)
    {
        this.image=image;
    }

    fun getStatus():Int
    {
        return status;
    }

    fun setStatus(status:Int)
    {
        if (status != null) {
            this.status=status
        };
    }

    fun getCategory_id():Int
    {
        return category_id;
    }

    fun setCategory_id(category_id:Int)
    {
        this.category_id=category_id;
    }

    fun getUpdatedAt():String
    {
        return update_date ;
    }

    fun setUpdatedAt(updatedAt:String)
    {
        this.update_date=updatedAt;
    }

    fun getReleaseDate():String
    {
        return release_date;
    }

    fun setReleaseDate(releaseDate:String)
    {
        this.release_date=releaseDate;
    }

    fun getSales():Int
    {
        return sales;
    }

    fun setSales(sales:Int)
    {
        this.sales=sales;
    }

    override fun toString(): String {
        return "Product(id=$id, name='$name', description='$description', size='$size', price_S=$price_S, price_M=$price_M, price_L=$price_L, image='$image', status=$status, category_id=$category_id, update_date='$update_date', release_date='$release_date', sales=$sales, is_disable=$is_disable)"
    }


}

package com.example.myapplication.modals

class Rating {
    private var id:Int=0;
    private  var user_id:Int = 0;
    private  var product_id:Int=0;
    private  var score:Int=0;
    private lateinit var comment :String
    private lateinit var create_at:String;
    private var is_disable: Boolean = false
    override fun toString(): String {
        return "Rating(id=$id, user_id=$user_id, product_id=$product_id, score=$score, comment='$comment', create_at='$create_at', is_disable=$is_disable)"
    }
}
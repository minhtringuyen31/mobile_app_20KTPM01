package com.example.myapplication.modals

class Rating(
    private var id:Int,
    private var user_id: String,
    private var user_name: String,
    private var user_image: String,
    private var product_id: Int,
    private var score: Float,
    private var comment: String,
    private var create_at: String,
    private var is_disalbe: Boolean
) {
    override fun toString(): String {
        return "Rating(id=$id, user_id='$user_id', user_name='$user_name', user_image='$user_image', product_id=$product_id, score=$score, comment='$comment', create_at='$create_at', is_disalbe=$is_disalbe)"
    }
    fun getUserName():String{
        return this.user_name
    }
    fun getUserImage():String{
        return this.user_image
    }

    fun getScore():Float{
        return this.score
    }

    fun getComment(): String{
        return this.comment
    }
    fun getDatePost(): String{
        return this.create_at
    }
}
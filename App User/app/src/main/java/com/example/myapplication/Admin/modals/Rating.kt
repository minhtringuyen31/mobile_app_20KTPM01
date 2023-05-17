package com.example.myapplication.Admin.modals


class Rating(
    private var id: Int? = null,
    private var user_id: Int? = null,
    private var user_name: String? = null,
    private var user_image: String? = null,
    private var product_id: Int? = null,
    private var score: Double? = null,
    private var comment: String? = null,
    private var create_at: String? = null,
    private var is_disable: Int? = null
) {
    fun getId(): Int? {
        return id
    }

    fun setId(id: Int?) {
        this.id = id
    }

    fun getUser_id(): Int? {
        return user_id
    }

    fun setUser_id(user_id: Int?) {
        this.user_id = user_id
    }

    fun getUser_name(): String? {
        return user_name
    }

    fun setUser_name(user_name: String?) {
        this.user_name = user_name
    }

    fun getUser_image(): String? {
        return user_image
    }

    fun setUser_image(user_image: String?) {
        this.user_image = user_image
    }

    fun getProduct_id(): Int? {
        return product_id
    }

    fun setProduct_id(product_id: Int?) {
        this.product_id = product_id
    }

    fun getScore(): Double? {
        return score
    }

    fun setScore(score: Double?) {
        this.score = score
    }

    fun getComment(): String? {
        return comment
    }

    fun setComment(comment: String?) {
        this.comment = comment
    }

    fun getCreate_at(): String? {
        return create_at
    }

    fun setCreate_at(create_at: String?) {
        this.create_at = create_at
    }

    fun getIs_disable(): Int? {
        return is_disable
    }

    fun setIs_disable(is_disable: Int?) {
        this.is_disable = is_disable
    }
}
package com.example.myapplication.Admin.modals


class Product(
    private var id: Int? = null,
    private var name: String? = null,
    private var description: String? = null,
    private var size: String? = null,
    private var price_S: Double? = null,
    private var price_M: Double? = null,
    private var price_L: Double? = null,
    private var note: String? = null,
    private var image: String? = null,
    private var status: Int? = null,
    private var category_id: Int? = null,
    private var update_date: String? = null,
    private var release_date: String? = null,
    private var sales: Int? = 0,
    private var is_disable: Int? = 0
) {
    fun getId(): Int? {
        return this.id
    }

    fun getName(): String? {
        return this.name
    }

    fun getDescription(): String? {
        return this.description
    }

    fun getSize(): String? {
        return this.size
    }

    fun getPriceS(): Double? {
        return this.price_S
    }

    fun getPriceM(): Double? {
        return this.price_M
    }

    fun getPriceL(): Double? {
        return this.price_L
    }

    fun getNote(): String? {
        return this.note
    }

    fun getImage(): String? {
        return this.image
    }

    fun getStatus(): Int? {
        return this.status
    }

    fun getCategoryId(): Int? {
        return this.category_id
    }

    fun getUpdateDate(): String? {
        return this.update_date
    }

    fun getReleaseDate(): String? {
        return this.release_date
    }

    fun getSales(): Int? {
        return this.sales
    }

    fun getIsDisable(): Int? {
        return this.is_disable
    }

    override fun toString(): String {
        return "Product(id=$id, name=$name, description=$description, size=$size, price_S=$price_S, price_M=$price_M, price_L=$price_L, note=$note, image=$image, status=$status, category_id=$category_id, updated_date=$update_date, release_date=$release_date, sales=$sales, is_disable=$is_disable)"
    }

    fun setSales(sales: Int) {
        this.sales = sales
    }

    fun setIsDisable(is_disable: Int) {
        this.is_disable = is_disable
    }

    fun setReleaseDate(release_date: String) {
        this.release_date = release_date
    }

    fun setUpdateDate(updated_date: String) {
        this.update_date = updated_date
    }

    fun setCategoryId(category_id: Int) {
        this.category_id = category_id
    }

    fun setStatus(status: Int) {
        this.status = status
    }

    fun setImage(image: String) {
        this.image = image
    }

    fun setNote(note: String) {
        this.note = note
    }

    fun setPriceL(price_L: Double) {
        this.price_L = price_L
    }

    fun setPriceM(price_M: Double) {
        this.price_M = price_M
    }

    fun setPriceS(price_S: Double) {
        this.price_S = price_S
    }

    fun setSize(size: String) {
        this.size = size
    }

    fun setDescription(description: String) {
        this.description = description
    }

    fun setName(name: String) {
        this.name = name
    }

    fun setId(id: Int) {
        this.id = id
    }

}
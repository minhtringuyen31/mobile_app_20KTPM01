package com.example.appadmin.modals

class Topping(
    private var category_id: Int?,
    private var name: String?,
    private var price: Double?,
    private var checked: Int?,
    private var id: Int?
) {
    fun getCategory_id(): Int? {
        return category_id
    }

    fun setCategory_id(category_id: Int?) {
        this.category_id = category_id
    }

    fun getName(): String? {
        return name
    }

    fun setName(name: String?) {
        this.name = name
    }

    fun getPrice(): Double? {
        return price
    }

    fun setPrice(price: Double?) {
        this.price = price
    }

    fun getId(): Int? {
        return id
    }

    fun setId(id: Int?) {
        this.id = id
    }

    fun getChecked(): Int? {
        return checked
    }

    fun setChecked(checked: Int?) {
        this.checked = checked
    }
}
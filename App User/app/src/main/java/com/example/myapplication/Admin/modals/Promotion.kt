package com.example.myapplication.Admin.modals

class countPromotion(
    private var count: Int? = null
) {
    fun getCount(): Int? {
        return this.count
    }
}
class Promotion(
    private var id: Int? = null,
    private var name: String? = null,
    private var description: String? = null,
    private var discount: Double? = null,
    private var start_date: String? = null,
    private var end_date: String? = null,
    private var image: String? = null,
    private var is_disable: Int? = null,
    private var quantity: Int? = null,
    private var code: String? = null
) {
    // Getter
    fun getId(): Int? {
        return id
    }

    fun getName(): String? {
        return name
    }

    fun getDescription(): String? {
        return description
    }

    fun getDiscount(): Double? {
        return discount
    }

    fun getStartDate(): String? {
        return start_date
    }

    fun getEndDate(): String? {
        return end_date
    }

    fun getImage(): String? {
        return image
    }

    fun getIsDisable(): Int? {
        return is_disable
    }

    fun getQuantity(): Int? {
        return quantity
    }

    fun getCode(): String? {
        return code
    }

    // Setter

    fun setId(id: Int?) {
        this.id = id
    }

    fun setName(name: String?) {
        this.name = name
    }

    fun setDescription(description: String?) {
        this.description = description
    }

    fun setDiscount(discount: Double?) {
        this.discount = discount
    }

    fun setStartDate(start_date: String?) {
        this.start_date = start_date
    }

    fun setEndDate(end_date: String?) {
        this.end_date = end_date
    }

    fun setImage(image: String?) {
        this.image = image
    }

    fun setIsDisable(is_disable: Int?) {
        this.is_disable = is_disable
    }

    fun setQuantity(quantity: Int?) {
        this.quantity = quantity
    }

    fun setCode(code: String?) {
        this.code = code
    }
}
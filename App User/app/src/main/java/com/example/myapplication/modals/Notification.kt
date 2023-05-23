package com.example.myapplication.modals

class Notification(
    private var id: Int,
    private var user_id: Int,
    private var title: String,
    private var sub_title: String,
    private var image: String,
    private var description: String,
    private var time: String,
    private var type: Int,
    private var is_seen: Int
) {
    fun getId(): Int{
        return id
    }
    fun getUserId():Int{
        return user_id
    }

    fun getTitle():String{
        return title
    }
    fun getSubTitle():String{
        return sub_title
    }

    fun getImage():String{
        return image
    }
    fun getDescription(): String{
        return description
    }
    fun getTime():String{
        return time.take(10)
    }
    fun getType():Int{
        return type
    }
    fun getIsSeen():Int{
        return is_seen
    }

    override fun toString(): String {
        return "Notification(id=$id, userId=$user_id, title='$title', subTitle='$sub_title', image='$image', description='$description', time='$time', type=$type, is_seen=$is_seen)"
    }


}
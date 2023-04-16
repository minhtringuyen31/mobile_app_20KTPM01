package com.example.myapplication.viewmodels

import androidx.lifecycle.ViewModel
import com.example.myapplication.modals.CartItem

class CountItemInBottomSheet: ViewModel(){
    var count = 1
    var total :Double =0.0
    var size:String="L"
    var nameTopping:String=""
}
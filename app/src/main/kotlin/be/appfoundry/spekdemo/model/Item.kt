package be.appfoundry.spekdemo.model

import android.content.Context
import android.graphics.drawable.Drawable

interface Item {
    var name : String
    val itemColorId : Int
    fun getIcon(context: Context) : Drawable
}
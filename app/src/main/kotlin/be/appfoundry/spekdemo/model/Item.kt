package be.appfoundry.spekdemo.model

import android.content.Context
import android.graphics.drawable.Drawable

/**
 * Created by simonvergauwen on 08/08/16.
 */
interface Item {
    var name : String
    val itemColorId : Int
    fun getIcon(context: Context) : Drawable
}
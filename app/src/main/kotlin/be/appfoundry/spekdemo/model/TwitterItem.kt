package be.appfoundry.spekdemo.model

import android.content.Context
import android.graphics.drawable.Drawable
import android.support.v4.content.ContextCompat
import be.appfoundry.spekdemo.R

open class TwitterItem(twitter: String) : Item {

    override var name: String = twitter

    override val itemColorId: Int
        get() = R.color.twitter

    override fun getIcon(context: Context): Drawable = ContextCompat.getDrawable(context, R.drawable.ic_twitter)
}
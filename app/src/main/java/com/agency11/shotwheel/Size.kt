package com.agency11.shotwheel

import android.content.Context
import android.view.View
import android.view.ViewGroup.MarginLayoutParams
import android.widget.TextView

class Size(mContext: Context) {

    val displayMetrics = mContext.resources.displayMetrics

    var dpWidth = displayMetrics.widthPixels
    var dpHeight = displayMetrics.heightPixels
    var density1 = displayMetrics.density
    var designWidth = 375
    var designHeight = 812
    var mContext: Context? = null

    fun Size(mContext: Context) {
        this.mContext = mContext
    }

    fun setSize(textView: TextView, size: Int) {
        textView.textSize = calcSize(size.toFloat()).toFloat()
    }

    fun setPadding(view: View, left: Int, top: Int, right: Int, bottom: Int) {
        view.setPadding(
            calcWidth(left.toFloat()),
            calcWidth(top.toFloat()),
            calcWidth(right.toFloat()),
            calcWidth(bottom.toFloat())
        )
    }

    fun setPadding2(view: View, left: Int, top: Int, right: Int, bottom: Int) {
        view.setPadding(
            calcSize(left.toFloat()),
            calcSize(top.toFloat()),
            calcSize(right.toFloat()),
            calcSize(bottom.toFloat())
        )
    }

    fun setMargin2(view: View, left: Int, top: Int, right: Int, bottom: Int) {
        val marginLayoutParams = view.layoutParams as MarginLayoutParams
        marginLayoutParams.setMargins(
            calcSize(left.toFloat()),
            calcSize(top.toFloat()),
            calcSize(right.toFloat()),
            calcSize(bottom.toFloat())
        )
    }

    fun setWidth(view: View, width: Int) {
        view.layoutParams.width = calcWidth(width.toFloat())
    }

    fun setHeight(view: View, height: Int) {
        view.layoutParams.height = calcWidth(height.toFloat())
    }

    fun setMargin(view: View, left: Int, top: Int, right: Int, bottom: Int) {
        val marginLayoutParams = view.layoutParams as MarginLayoutParams
        marginLayoutParams.setMargins(
            calcWidth(left.toFloat()),
            calcWidth(top.toFloat()),
            calcWidth(right.toFloat()),
            calcWidth(bottom.toFloat())
        )
    }

   private fun calcHeight(value: Float): Int {
        return (dpWidth * (value / designHeight)).toInt()
    }

   private fun calcWidth(value: Float): Int {
        return (dpWidth * (value / designWidth)).toInt()
    }

   private fun calcSize(value: Float): Int {
        return (dpWidth * (value / designWidth) / density1).toInt()
    }

}
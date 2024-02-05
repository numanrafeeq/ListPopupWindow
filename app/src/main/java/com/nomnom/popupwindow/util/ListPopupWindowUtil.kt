package com.nomnom.popupwindow.util

import android.content.Context
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.FrameLayout
import android.widget.ListAdapter
import android.widget.ListPopupWindow
import androidx.core.content.ContextCompat

object ListPopupWindowUtil {

    fun getPlainListPopUpWindow(
        context: Context,
        list: List<String>,
        anchor: View,
        backgroundDrawableRes: Int,
        cellLayoutRes: Int,
        horizontalOffsetValue: Int = 0,
        verticalOffsetValue: Int = 0,
        itemClickListener: AdapterView.OnItemClickListener? = null
    ): ListPopupWindow {

        val adapter = ArrayAdapter(context, cellLayoutRes, list)
        val listPopupWindow = ListPopupWindow(context)
        listPopupWindow.setAdapter(adapter)
        listPopupWindow.width = measureContentWidth(context, adapter)
        listPopupWindow.height = -2
        listPopupWindow.isModal = true
        listPopupWindow.anchorView = anchor
        listPopupWindow.horizontalOffset = horizontalOffsetValue
        listPopupWindow.verticalOffset = verticalOffsetValue
        if (backgroundDrawableRes != 0) {
            listPopupWindow.setBackgroundDrawable(ContextCompat.getDrawable(context, backgroundDrawableRes))
        }




        if (itemClickListener != null) {
            listPopupWindow.setOnItemClickListener(itemClickListener)
        }

        return listPopupWindow
    }



    private fun measureContentWidth(context: Context, adapter: ListAdapter): Int {
        val measureParentViewGroup = FrameLayout(context)
        var itemView: View? = null
        var maxWidth = 0
        var itemType = 0
        val widthMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED)
        val heightMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED)
        val count = adapter.count
        for (index in 0 until count) {
            val positionType = adapter.getItemViewType(index)
            if (positionType != itemType) {
                itemType = positionType
                itemView = null
            }
            itemView = adapter.getView(index, itemView, measureParentViewGroup)
            itemView.measure(widthMeasureSpec, heightMeasureSpec)

            val itemWidth = itemView.measuredWidth
            if (itemWidth > maxWidth) {
                maxWidth = itemWidth
            }
        }
        return maxWidth
    }
}

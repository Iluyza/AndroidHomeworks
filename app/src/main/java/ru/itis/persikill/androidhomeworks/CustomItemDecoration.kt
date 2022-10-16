package ru.itis.persikill.androidhomeworks

import android.graphics.Canvas
import android.graphics.drawable.Drawable
import androidx.recyclerview.widget.RecyclerView

class CustomItemDecoration(private val divider: Drawable, private val spacingDP: Int) :
    RecyclerView.ItemDecoration() {

    override fun onDraw(canvas: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        val left = parent.paddingLeft + (24).convertDpToPx(parent.context)
        val right = parent.width - parent.paddingRight - (24).convertDpToPx(parent.context)
        val childCount = parent.childCount


        for (i in 0 until childCount) {
            val child = parent.getChildAt(i)

            val params = child.layoutParams as RecyclerView.LayoutParams

            val top = child.bottom + params.bottomMargin
            val bottom = top + divider.intrinsicHeight

            params.bottomMargin = spacingDP
            params.rightMargin = spacingDP
            params.leftMargin = spacingDP
            params.topMargin = spacingDP

            divider.setBounds(left, top, right, bottom)
            divider.draw(canvas)
        }

    }

}
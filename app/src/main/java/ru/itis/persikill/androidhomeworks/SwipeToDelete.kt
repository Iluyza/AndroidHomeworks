package ru.itis.persikill.androidhomeworks

import android.view.View
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView


class SwipeToDelete(adapter: MyAdapter?) :

    ItemTouchHelper(object : SimpleCallback(0, ItemTouchHelper.RIGHT) {
        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean = false


        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            Repository.deleteItem(viewHolder.adapterPosition)
            adapter?.submitList(Repository.dataList)
        }
    })

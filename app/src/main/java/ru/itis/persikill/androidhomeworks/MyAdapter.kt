package ru.itis.persikill.androidhomeworks

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil.ItemCallback
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class MyAdapter(
    val differ: ItemCallback<MyLogic>,
    val onItemClicked: (()-> Unit)?,
    val onDeleteClicked: ((Int)-> Unit)
) :
    ListAdapter<MyLogic, RecyclerView.ViewHolder>(differ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        when (viewType) {
            R.layout.advertisement_layout -> AdvertisementViewHolder.create(parent)
            R.layout.item_layout -> ItemViewHolder.create(parent, onItemClicked, onDeleteClicked)
            else -> throw IllegalArgumentException("There is no viewHolder for such an item! : )")
        }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = currentList[position]
        when (holder) {
            is AdvertisementViewHolder -> holder.onBind(item as MyLogic.Advertisement)
            is ItemViewHolder -> holder.onBind(item as MyLogic.Item)
        }
    }

    override fun getItemViewType(position: Int): Int =
        when (currentList[position]) {
            is MyLogic.Item -> R.layout.item_layout
            is MyLogic.Advertisement -> R.layout.advertisement_layout
        }

}
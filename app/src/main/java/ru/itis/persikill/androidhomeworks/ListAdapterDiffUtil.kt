package ru.itis.persikill.androidhomeworks


import androidx.recyclerview.widget.DiffUtil

class ListAdapterDiffUtil : DiffUtil.ItemCallback<MyLogic>() {
    override fun areItemsTheSame(oldItem: MyLogic, newItem: MyLogic): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: MyLogic, newItem: MyLogic): Boolean =
        oldItem == newItem

}
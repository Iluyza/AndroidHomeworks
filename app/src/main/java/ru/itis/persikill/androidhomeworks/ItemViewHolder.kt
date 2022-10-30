package ru.itis.persikill.androidhomeworks

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.itis.persikill.androidhomeworks.databinding.ItemLayoutBinding

class ItemViewHolder(
    val binding: ItemLayoutBinding,
    val onItemClicked: (() -> Unit)?,
    val onDeleteClicked: ((Int) -> Unit)?,
) : RecyclerView.ViewHolder(binding.root) {

    init {
        with(binding) {
            root.setOnClickListener {
                onItemClicked?.invoke()
            }
            ibDelete.setOnClickListener {
                onDeleteClicked?.invoke(adapterPosition)
            }
        }
    }

    fun onBind(item: MyLogic.Item) {
        with(binding) {
            tvTitle.text = item.title
            tvDescription.text = item.description
        }
    }


    companion object {
        fun create(
            parent: ViewGroup,
            onItemClicked: (() -> Unit)?,
            onDeleteClicked: ((Int) -> Unit)?
        ): ItemViewHolder = ItemViewHolder(
            binding = ItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            onItemClicked = onItemClicked,
            onDeleteClicked = onDeleteClicked,
        )
    }
}
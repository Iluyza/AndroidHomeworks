package ru.itis.persikill.androidhomeworks

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import ru.itis.persikill.androidhomeworks.databinding.AdvertisementLayoutBinding

class AdvertisementViewHolder(val binding: AdvertisementLayoutBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun onBind(item: MyLogic.Advertisement) {
        with(binding) {
            ivAd.load(item.imageUrl)
            tvTitle.text = item.title
        }
    }

    companion object {
        fun create(parent: ViewGroup): AdvertisementViewHolder = AdvertisementViewHolder(
            AdvertisementLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        )
    }
}

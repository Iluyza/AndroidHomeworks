package ru.itis.persikill.androidhomeworks.adapter

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import ru.itis.persikill.androidhomeworks.databinding.ItemFilmBinding
import ru.itis.persikill.androidhomeworks.model.Film

class FilmItem(
    private val binding: ItemFilmBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun onBind(film: Film) {
        with(binding) {

        }
    }
}
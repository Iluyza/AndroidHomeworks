package ru.itis.persikill.androidhomeworks.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import ru.itis.persikill.androidhomeworks.FilmHolder
import ru.itis.persikill.androidhomeworks.databinding.ItemFilmBinding
import ru.itis.persikill.androidhomeworks.model.Film

class FilmAdapter(
    private var list: List<Film>,
    private val glide: RequestManager,
    private val onItemClick: (Int) -> Unit,
) : RecyclerView.Adapter<FilmHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FilmHolder = FilmHolder(
        binding = ItemFilmBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ),
        glide = glide,
        onItemClick = onItemClick
    )

    override fun onBindViewHolder(holder: FilmHolder, position: Int) {
        holder.onBind(list[position])
    }

    override fun getItemCount(): Int = list.size


}
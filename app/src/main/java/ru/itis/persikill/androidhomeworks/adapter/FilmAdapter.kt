package ru.itis.persikill.androidhomeworks.adapter

import FilmRepository.films
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener

import ru.itis.persikill.androidhomeworks.databinding.ItemFilmBinding
import ru.itis.persikill.androidhomeworks.model.Film
import javax.sql.DataSource

class FilmAdapter (
    private val list: List<Film>,
    private val glide: RequestManager,
    private val onItemClick: (Int) -> Unit
) : RecyclerView.Adapter<FilmAdapter.FilmViewHolder>() {


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FilmViewHolder = FilmViewHolder(
        ItemFilmBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ),
        onItemClick = onItemClick,
        glide = glide
    )
    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(
        holder: FilmViewHolder,
        position: Int
    ) {
        holder.bind(films[position])
    }



    inner class FilmViewHolder(
        private val binding: ItemFilmBinding,
        val onItemClick: (Int) -> Unit,
        private val glide: RequestManager,
    ) : RecyclerView.ViewHolder(binding.root) {
        init {
            with(binding) {
                root.setOnClickListener {
                    onItemClick(adapterPosition)
                }
            }
        }

        fun bind(film: Film) {
            with(binding) {
                tvFilmName.text = film.name
                tvCountry.text = film.county
                glide
                    .load(film.url)
                    .into(ivFilmPoster)
            }
        }
    }
}
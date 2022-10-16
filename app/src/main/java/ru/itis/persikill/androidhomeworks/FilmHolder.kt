package ru.itis.persikill.androidhomeworks

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import ru.itis.persikill.androidhomeworks.databinding.ItemFilmBinding
import ru.itis.persikill.androidhomeworks.model.Film

class FilmHolder(
    private val binding: ItemFilmBinding,
    private val glide: RequestManager,
    private val onItemClick: (Int) -> Unit,
) : RecyclerView.ViewHolder(binding.root) {

    private val options: RequestOptions = RequestOptions()
        .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)

    fun onBind(film: Film) {
        with(binding) {
            tvFilmName.text = film.name
            root.setOnClickListener {
                onItemClick(film.id)
            }
            glide
                .load(film.url)
                .placeholder(R.drawable.test_film)
                .error(R.drawable.test_film)
                .into(ivFilmPoster)
        }
    }
}
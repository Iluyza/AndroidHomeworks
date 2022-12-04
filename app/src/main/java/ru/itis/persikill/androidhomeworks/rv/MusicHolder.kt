package ru.itis.persikill.androidhomeworks.rv

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.itis.persikill.androidhomeworks.service.Music
import ru.itis.persikill.androidhomeworks.service.MusicRepository
import ru.itis.persikill.androidhomeworks.databinding.ItemMusicBinding
import java.util.*

class MusicHolder(
    private val binding: ItemMusicBinding,
    private val onItemChosenAction: (Int) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    private var currentMusic: Music? = null

    init {
        itemView.setOnClickListener {
            currentMusic?.run {
                onItemChosenAction(this.id)
            }
        }
    }

    fun bind(itemId: Int) {
        this.currentMusic = MusicRepository.findMusicById(itemId)

        currentMusic?.let { music ->
            with(binding) {
                tvItemTrackTitle.text = music.title
                tvItemTrackArtist.text = music.artist
                ivItemTrackCover.setImageResource(music.coverId)
                val minutes = (music.duration % 3600) / 60
                val seconds = music.duration % 60
                tvItemTrackDuration.text =
                    String.format(Locale.ENGLISH, "%02d:%02d", minutes, seconds)
            }
        }
    }

    companion object {
        fun createHolder(
            parent: ViewGroup,
            onItemChosenAction: (Int) -> Unit
        ) = MusicHolder(
            ItemMusicBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ), onItemChosenAction
        )
    }
}
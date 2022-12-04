package ru.itis.persikill.androidhomeworks.rv

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.itis.persikill.androidhomeworks.service.Music

class MusicAdapter (
    private val list: List<Music>,
    private val onItemChosenAction: (Int) -> Unit
) : RecyclerView.Adapter<MusicHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MusicHolder {
        return MusicHolder.createHolder(parent, onItemChosenAction)
    }

    override fun onBindViewHolder(holder: MusicHolder, position: Int) {
        holder.bind(list[position].id)
    }

    override fun getItemCount(): Int = list.size
}
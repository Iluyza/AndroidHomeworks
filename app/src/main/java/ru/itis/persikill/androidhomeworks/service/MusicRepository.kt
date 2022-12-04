package ru.itis.persikill.androidhomeworks.service

import ru.itis.persikill.androidhomeworks.R


object MusicRepository {
    var cursor: Int = 0

    private val musics = arrayListOf(
        Music(cursor++, "Мэтэутамак", "Ришат Тухватуллин", 127, R.drawable.rishat, R.raw.rishatt),
        Music(cursor++, "Яз килэ", "Элвин Грей", 150, R.drawable.elvin_grey, R.raw.elvin_grey),
        Music(cursor++, "Амин Асин Абез", "Нурманский", 110, R.drawable.nurminsky, R.raw.nurminsky),
        Music(cursor++, "Кубелек", "Ришат Тухватуллин", 140, R.drawable.rishat, R.raw.rishatt2),
        Music(cursor++, "Мин яратам сине тормош", "Гузель Уразова", 170, R.drawable.guzel, R.raw.guzel_urazova),
        Music(cursor++, "Эзли кунел", "Элвин Грей", 165, R.drawable.elvin_grey, R.raw.elvin_grey2),
        Music(cursor++, "Син бит минем шатлыгым", "Анвар Нургалиев", 281, R.drawable.anvar, R.raw.anvar)
    )

    fun getMusics(): List<Music> {
        return musics;
    }

    fun findMusicById(itemId: Int): Music? {
        return if (itemId in musics.indices)
            musics[itemId]
        else
            null
    }
}
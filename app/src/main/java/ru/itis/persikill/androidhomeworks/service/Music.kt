package ru.itis.persikill.androidhomeworks.service


import androidx.annotation.DrawableRes
import androidx.annotation.RawRes

data class Music(
    val id: Int,
    val title: String,
    val artist: String,
    val duration: Int,
    @DrawableRes val coverId: Int,
    @RawRes val rawFileId: Int
)
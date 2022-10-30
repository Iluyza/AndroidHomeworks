package ru.itis.persikill.androidhomeworks


sealed class MyLogic(val id: Int) {

    data class Advertisement(val title: String, val imageUrl: String): MyLogic(0)

    data class Item(val description: String, var title: String): MyLogic(0)
}

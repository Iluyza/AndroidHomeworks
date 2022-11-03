package ru.itis.persikill.androidhomeworks

import android.os.Bundle

interface NavigationFragmentInterface {
    val ARG: String
    fun createBundle(name: String) : Bundle
}
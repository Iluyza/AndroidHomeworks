package ru.itis.persikill.androidhomeworks.fragment

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.graphics.toColor
import ru.itis.persikill.androidhomeworks.R

import ru.itis.persikill.androidhomeworks.databinding.Fragment2Binding

class Fragment2 : Fragment(R.layout.fragment_2) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = Fragment2Binding.bind(view)
        val counter = arguments?.getInt("COUNTER") ?: 0

        if (counter == 0)
            binding.textView.visibility = View.INVISIBLE

        if (counter > 0)
            binding.textView.visibility = View.VISIBLE
        with(binding) {
            when (counter) {
                in 0..50 -> {
                    scrSecond.setBackgroundColor(Color.GREEN)
                }
                in 51..100 ->
                    scrSecond.setBackgroundColor(Color.BLUE)
                in 100..Integer.MAX_VALUE ->
                    scrSecond.setBackgroundColor(Color.RED)
            }
        }
        binding.textView.text = "Counter value: $counter"
    }

}
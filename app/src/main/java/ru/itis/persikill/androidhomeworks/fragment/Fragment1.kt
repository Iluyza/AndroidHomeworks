package ru.itis.persikill.androidhomeworks.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import ru.itis.persikill.androidhomeworks.CustomDialogFragment
import ru.itis.persikill.androidhomeworks.R
import ru.itis.persikill.androidhomeworks.databinding.Fragment1Binding
import ru.itis.persikill.androidhomeworks.showDialog


class Fragment1 : Fragment(R.layout.fragment_1) {
    private var counter = 0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = Fragment1Binding.bind(view)
        val fragmentManager = activity?.supportFragmentManager
        val textCount = binding.tvCount
        with(binding) {
            btnFirst.setOnClickListener {
                fragmentManager?.beginTransaction()?.apply {
                    val fragment = Fragment2()
                    val bundle = Bundle()
                    bundle.putInt("COUNTER", counter)
//                    bundle.putInt("PIC", imageId)  // это что такое??
                    fragment.arguments = bundle
                    setCustomAnimations(
                        android.R.anim.slide_in_left,
                        android.R.anim.slide_out_right
                    )
                    replace(R.id.fragments_container, fragment)
                    addToBackStack("second fragment")
                    commit()
                }
            }
            btnSecond.setOnClickListener {
                counter++
                textCount.text = "Counter value: $counter"
            }
            btnThird.setOnClickListener {
                val dialog = CustomDialogFragment(counterValue = counter,
                    { counterFromDialogFragment ->
                        counter = counterFromDialogFragment
                        tvCount.setText("Counter value: $counter")
                    }
                )
                dialog.show(parentFragmentManager, "Custom dialog")
            }

        }
    }

    private fun showDialog2() {
        showDialog(
            title = "Введите число",
            positiveAction = {
            },
        )

        return
        showDialog(
            title = "New title",
            positiveAction = {
                parentFragmentManager.beginTransaction()
                    .replace(R.id.fragments_container, Fragment2())
                    .commit()
            },
            negativeAction = {},
            neutralAction = {}
        )
    }
}
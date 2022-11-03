package ru.itis.persikill.androidhomeworks

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

class FinanceFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_finance, container, false)
    }

    companion object : NavigationFragmentInterface {

        override val ARG = "FAVORITES_FRAGMENT"
        override fun createBundle(name: String) = Bundle().apply {
            putString(ARG, name)
        }
    }
}
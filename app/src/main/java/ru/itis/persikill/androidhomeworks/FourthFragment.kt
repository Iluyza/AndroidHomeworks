package ru.itis.persikill.androidhomeworks

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.itis.persikill.androidhomeworks.databinding.FragmentFourthBinding

class FourthFragment : Fragment() {

    private var _binding: FragmentFourthBinding? = null
    private val binding by lazy {_binding!!}
    private var className: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments.let {
            className = it?.getString(ARG)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFourthBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initLayouts()
    }
    private fun initLayouts() {
        with(binding.includer) {
            tvFragmentName.text = className
            btnNavigate.setOnClickListener {
            }
        }
    }
    companion object : NavigationFragmentInterface{
        override val ARG: String = "FORTH_FRAGMENT"
        override fun createBundle(name: String) = Bundle().apply {
            putString(ARG, name)
        }
    }
}

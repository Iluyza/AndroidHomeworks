package ru.itis.persikill.androidhomeworks

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.itis.persikill.androidhomeworks.databinding.FragmentThirdBinding
import ru.itis.persikill.androidhomeworks.NavigationFragmentInterface
import ru.itis.persikill.androidhomeworks.navigate



class ThirdFragment : Fragment() {
    private var _binding: FragmentThirdBinding? = null
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
        _binding = FragmentThirdBinding.inflate(layoutInflater)
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
                navigate(R.id.action_thirdFragment_to_fourthFragment,FourthFragment)
            }
        }
    }

    companion object : NavigationFragmentInterface{
        override val ARG = "THIRD_FRAGMENT"
        override fun createBundle(name: String) = Bundle().apply {
            putString(ARG, name)
        }
    }
}
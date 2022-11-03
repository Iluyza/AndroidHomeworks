package ru.itis.persikill.androidhomeworks

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.itis.persikill.androidhomeworks.databinding.FragmentFirstBinding
import android.view.LayoutInflater as LayoutInflater1

class FirstFragment : Fragment() {
    private var _binding: FragmentFirstBinding? = null
    private val binding by lazy {_binding!!}
    private var className: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        className = arguments?.getString(ARG)?: FirstFragment.getClassName()

    }

    override fun onCreateView(
        inflater: LayoutInflater1, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFirstBinding.inflate(layoutInflater)
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
                navigate(R.id.action_firstFragment_to_secondFragment,SecondFragment)
            }
        }
    }


    companion object: NavigationFragmentInterface {
        override val ARG: String = "FIRST_FRAGMENT"
        override fun createBundle(name: String) = Bundle().apply {
            putString(ARG, name)
        }
    }
}
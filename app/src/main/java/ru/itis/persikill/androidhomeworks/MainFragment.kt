package ru.itis.persikill.androidhomeworks

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import ru.itis.persikill.androidhomeworks.adapter.FilmAdapter
import ru.itis.persikill.androidhomeworks.databinding.FragmentMainBinding

class MainFragment : Fragment(R.layout.fragment_main) {
    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    private var adapter: FilmAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentMainBinding.bind(view)
        initAdapter()
    }

    private fun initAdapter() {
        adapter = FilmAdapter(
            FilmRepository.films,
            Glide.with(this)
        ) {
            findNavController().navigate(
                R.id.action_mainFragment_to_filmFragment,
                FilmFragment.createBundle(it),
            )
        }
        binding.rvFilms.adapter = adapter
        binding.rvFilms.layoutManager = GridLayoutManager(requireContext(), 2)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
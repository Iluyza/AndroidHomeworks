package ru.itis.persikill.androidhomeworks.adapter
import android.os.Bundle

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import ru.itis.persikill.androidhomeworks.databinding.FragmentDescriptionBinding


class DescriptionFragment : Fragment() {
    private var _binding: FragmentDescriptionBinding? = null
    private val binding by lazy { _binding!!}

    private var filmsID: Int  = -100


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        filmsID = arguments?.getInt(FILM_ID_TAG)?:-100

        _binding = FragmentDescriptionBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initLayout()
    }

    private fun initLayout() {
        val film = FilmRepository.films[filmsID]

        with(binding) {
            tvDescription.text = film.description

            Glide.with(root)
                .load(film.url)
                .error(androidx.vectordrawable.R.drawable.notify_panel_notification_icon_bg)
                .into(ivFilm)
        }
    }

    companion object {
        const val DESCRIPTION_FRAGMENT_TAG = "DESCRIPTION_FRAGMENT_TAG"
        const val FILM_ID_TAG = "FILM_ID_TAG"
        fun newInstance(itemPosition : Int) =
            DescriptionFragment().apply {
                arguments = Bundle().apply {
                    putInt(FILM_ID_TAG, itemPosition)
                }
            }
    }
}
package ru.itis.persikill.androidhomeworks
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.bumptech.glide.Glide
import ru.itis.persikill.androidhomeworks.databinding.FragmentFilmBinding
import ru.itis.persikill.androidhomeworks.model.Film

class FilmFragment : Fragment(R.layout.fragment_film) {

    private var _binding: FragmentFilmBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentFilmBinding.bind(view)
        val id = arguments?.getInt(ID)
        val film: Film = FilmRepository.films[id!!]
        val glide = Glide.with(this)
        with(binding) {
            glide
                .load(film.url)
                .placeholder(R.drawable.test_film)
                .error(R.drawable.test_film)
                .into(ivPoster)
            tvName.text = film.name
            tvDirector.text = film.director
            tvYear.text = film.year.toString()
            tvCountry.text = film.county
            tvDescription.text = film.description
        }
    }

    companion object {
        private const val ID = "id"
        fun createBundle(id: Int): Bundle {
            val bundle = Bundle()
            bundle.putInt(ID, id)
            return bundle
        }
    }
}

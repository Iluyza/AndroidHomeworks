package ru.itis.persikill.androidhomeworks.service

import android.nfc.NfcAdapter.EXTRA_ID
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.recyclerview.widget.DividerItemDecoration
import ru.itis.persikill.androidhomeworks.R
import ru.itis.persikill.androidhomeworks.databinding.FragmentMusicsBinding
import ru.itis.persikill.androidhomeworks.rv.MusicAdapter


class MusicsFragment : Fragment(R.layout.fragment_musics) {

    private var binding: FragmentMusicsBinding? = null
    private var trackAdapter: MusicAdapter? = null


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMusicsBinding.bind(view)

        trackAdapter = MusicAdapter(MusicRepository.getMusics()) {
            onTrackChosenAction(it)
        }

        binding?.rvMusics?.run {
            adapter = trackAdapter
            addItemDecoration(DividerItemDecoration(this.context, DividerItemDecoration.VERTICAL))
        }
    }
    private fun onTrackChosenAction(trackId: Int) {
        val bundle = Bundle().apply {
            putInt(EXTRA_ID, trackId)
        }
        val newFragment = OneMusicFragment()
        newFragment.arguments = bundle

        parentFragmentManager.beginTransaction()
            .setCustomAnimations(
                R.anim.from_right,
                R.anim.fade_out,
                R.anim.fade_in,
                R.anim.exit_right
            )
            .replace(R.id.fragment_container, newFragment)
            .addToBackStack(null)
            .commit()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
        trackAdapter = null
    }
}
package ru.itis.persikill.androidhomeworks.service

import android.app.Notification.EXTRA_PROGRESS
import android.content.ComponentName
import android.content.Context.BIND_AUTO_CREATE
import android.content.Intent
import android.content.ServiceConnection
import android.nfc.NfcAdapter.EXTRA_ID
import android.os.Bundle
import android.os.IBinder
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.SeekBar
import com.google.android.material.snackbar.Snackbar
import ru.itis.persikill.androidhomeworks.R
import ru.itis.persikill.androidhomeworks.databinding.FragmentOneMusicBinding

class OneMusicFragment: Fragment(R.layout.fragment_one_music) {

    private var binding: FragmentOneMusicBinding? = null
    private var binder: MusicPlayerService.MusicPlayerBinder? = null
    private var seekBar: SeekBar? = null

    private val connection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            binder = service as? MusicPlayerService.MusicPlayerBinder
            arguments?.getInt(EXTRA_ID)?.let {
                val progress = arguments?.getInt(EXTRA_PROGRESS)
                if (progress != null) {
                    binder?.playTrackFromPosition(it, progress)
                } else {
                    binder?.playTrack(it)
                }
                initializeView(it)
            }
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            binder = null
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activity?.bindService(
            Intent(requireContext(), MusicPlayerService::class.java),
            connection,
            BIND_AUTO_CREATE
        )
        seekBar = binding?.seekBar

    }

    override fun onResume() {
        super.onResume()
        binder?.getCurrentTrack()?.id?.let {
            initializeView(it)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentOneMusicBinding.bind(view)

        initListeners()
    }

    private fun initListeners() {
        binding?.run {
            btnNext.setOnClickListener {
                playNextTrack()
            }

            btnPausePlay.setOnClickListener {
                pauseOrPlayTrack()
            }

            btnPrevious.setOnClickListener {
                playPreviousSong()
            }
            btnStop.setOnClickListener {
                stopPlayingSong()
            }
        }
    }

    private fun seekBar (trackId: Int,int: Int) {
        with(binding) {
            this?.seekBar?.progress = 0
            this?.seekBar?.max = trackId.let { MusicRepository.findMusicById(it) }?.duration ?: -1

        }
    }

    private fun initializeView(trackId: Int) {
        val repoTrack = trackId.let { MusicRepository.findMusicById(it) }
        binding?.run {
            repoTrack?.let { track ->
                ivCover.setImageResource(track.coverId)
                tvArtist.text = track.artist
                tvTitle.text = track.title
            }
            btnPausePlay.setImageResource(R.drawable.pause)
        }
    }

    private fun playNextTrack() {
        binder?.playNextTrack()
        binder?.getCurrentTrack()?.id?.let {
            initializeView(it)
        }

    }

    private fun playPreviousSong() {
        binder?.playPreviousTrack()
        binder?.getCurrentTrack()?.id?.let {
            initializeView(it)
        }

    }

    private fun pauseOrPlayTrack() {
        if (binder?.isPlaying() == true) {
            binder?.pause()
            binding?.btnPausePlay?.setImageResource(R.drawable.play)
        } else {
            binder?.play()
            binding?.btnPausePlay?.setImageResource(R.drawable.pause)
        }
    }

    private fun stopPlayingSong() {
        binder?.stop()
        binding?.btnPausePlay?.setImageResource(R.drawable.play)
    }

    private fun showMessage(message: String) {
        Snackbar.make(
            requireActivity().findViewById(R.id.fragment_container),
            message,
            Snackbar.LENGTH_LONG
        ).show()
    }
}
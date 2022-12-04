package ru.itis.persikill.androidhomeworks.service

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.Binder
import android.os.IBinder
import android.widget.SeekBar
import ru.itis.persikill.androidhomeworks.R
import ru.itis.persikill.androidhomeworks.notification.PlayerNotificationManager


class MusicPlayerService : Service() {
    private lateinit var mediaPlayer: MediaPlayer
    private lateinit var notificationManager: PlayerNotificationManager
    private lateinit var trackList: List<Music>
    private var currentTrackId: Int = -1
    private lateinit var seekBar: SeekBar


    override fun onBind(intent: Intent?): IBinder = MusicPlayerBinder()

    inner class MusicPlayerBinder : Binder() {
        fun playTrack(trackId: Int) = this@MusicPlayerService.playTrack(trackId)
        fun play() = this@MusicPlayerService.playCurrentSong()
        fun pause() = this@MusicPlayerService.pauseCurrentSong()
        fun stop() = this@MusicPlayerService.stopPlayingCurrentSong()
        fun playNextTrack() = this@MusicPlayerService.playNextSong()
        fun playPreviousTrack() = this@MusicPlayerService.playPreviousSong()
        fun getCurrentTrack(): Music? = this@MusicPlayerService.getCurrentTrack()
        fun isPlaying(): Boolean = this@MusicPlayerService.isPlaying()
        fun playTrackFromPosition(id: Int, progress: Int) = this@MusicPlayerService.playTrackFromPosition(id, progress)
//        fun onProgressChanges(mSeekBar: SeekBar,progress: Int,boolean: Boolean) = this@MusicPlayerService.playTrackOnPosition(mSeekBar,progress,boolean)
    }



    private fun playTrackFromPosition(id: Int, progress: Int) {
        playTrack(id)
        playCurrentFromPosition(progress)
    }

    private fun playCurrentFromPosition(progress: Int) = mediaPlayer.seekTo(progress)

    override fun onCreate() {
        super.onCreate()
        trackList = MusicRepository.getMusics()
        notificationManager = PlayerNotificationManager(this)
        mediaPlayer = MediaPlayer()
    }



    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        when(intent?.action) {
            this.getString(R.string.previous) -> playPreviousSong()
            this.getString(R.string.next) -> playNextSong()
            this.getString(R.string.resume) -> {
                if (mediaPlayer.isPlaying) pauseCurrentSong() else playCurrentSong()
            }
            this.getString(R.string.stop) -> stopPlayingCurrentSong()
        }

        return START_REDELIVER_INTENT
    }

    private fun pauseCurrentSong() {
        mediaPlayer.pause()
    }

    private fun playCurrentSong() {
        mediaPlayer.start()
//        handler.postDelayed(p_bar,1000)
    }

    private fun stopPlayingCurrentSong() {
        mediaPlayer.stop()
        mediaPlayer.prepare()
    }

    private fun getCurrentTrack(): Music? {
        return if (currentTrackId in 0..trackList.size)
            trackList[currentTrackId]
        else
            null
    }


    private fun playPreviousSong() {
        currentTrackId =
            if (currentTrackId > -1) {
                if (currentTrackId == 0)
                    trackList.size - 1
                else
                    currentTrackId - 1
            } else {
                0
            }
        playTrack(currentTrackId)
    }

    private fun playNextSong() {
        currentTrackId =
            if (currentTrackId > -1) {
                if (currentTrackId == trackList.size - 1)
                    0
                else
                    currentTrackId + 1
            } else {
                0
            }
        playTrack(currentTrackId)
    }

    private fun playTrack(trackId: Int) {
        if (mediaPlayer.isPlaying)
            mediaPlayer.stop()

        if (checkIdIsCorrect(trackId)) {
            currentTrackId = trackId

            mediaPlayer = MediaPlayer.create(applicationContext, trackList[currentTrackId].rawFileId)
            mediaPlayer.run {
                start()
                setOnCompletionListener {
                    playNextSong()
                }
            }

            notificationManager.createNotification(trackId, mediaPlayer)
        } else {
            throw MusicPlayerException("Wrong id = $trackId for a track. Can not play.")
        }
    }

    private fun isPlaying(): Boolean = mediaPlayer.isPlaying

    private fun checkIdIsCorrect(id: Int): Boolean {
        return id in trackList.indices
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer.release()
    }
}
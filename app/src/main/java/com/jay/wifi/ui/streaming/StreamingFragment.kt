package com.jay.wifi.ui.streaming

import android.net.Uri
import android.os.Bundle
import android.view.View
import com.base.library.ui.BaseFragment
import com.jay.wifi.R
import com.jay.wifi.databinding.FragmentStreamingBinding
import dagger.hilt.android.AndroidEntryPoint
import org.videolan.libvlc.IVLCVout
import org.videolan.libvlc.LibVLC
import org.videolan.libvlc.Media
import org.videolan.libvlc.MediaPlayer

@AndroidEntryPoint
class StreamingFragment : BaseFragment<FragmentStreamingBinding, StreamingViewModel>(
    R.layout.fragment_streaming
), IVLCVout.Callback, MediaPlayer.EventListener {
    private val libVlc by lazy {
        LibVLC(context, OPTIONS)
    }
    private val mediaPlayer by lazy {
        MediaPlayer(libVlc)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupUi()
    }

    private fun setupUi() {
        binding.svStreaming.holder.setKeepScreenOn(true)
        mediaPlayer.setEventListener(this)

        mediaPlayer.vlcVout.run {
            setVideoView(binding.svStreaming)
            addCallback(this@StreamingFragment)
            attachViews()
        }

        mediaPlayer.media = Media(libVlc, Uri.parse("rtsp://$IP:$PORT")).apply {
            setHWDecoderEnabled(true, false)
            addOption(":network-caching=150")
            addOption(":clock-jitter=0")
            addOption(":clock-synchro=0")
            addOption(":fullscreen")
        }
        mediaPlayer.aspectRatio = "4:3"
        mediaPlayer.play()
    }

    override fun onDestroy() {
        releasePlayer()
        super.onDestroy()
    }

    private fun releasePlayer() {
        mediaPlayer.stop()

        mediaPlayer.vlcVout.run {
            removeCallback(this@StreamingFragment)
            detachViews()
        }
        libVlc.release()
    }

    /**********/
    override fun onSurfacesCreated(vlcVout: IVLCVout?) {

    }

    override fun onSurfacesDestroyed(vlcVout: IVLCVout?) {

    }

    /**********/
    override fun onEvent(event: MediaPlayer.Event) {
        when (event.type) {
            MediaPlayer.Event.EndReached -> releasePlayer()
        }
    }

    companion object {
        private const val IP = "192.168.0.29"
        private const val PORT = 1234

        private val OPTIONS = arrayListOf(
            "--avcodec-codec=h264",
            "--file-caching=2000",
            "-vvv"
        )
    }

}
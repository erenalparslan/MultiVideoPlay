package com.erenalparslan.multivideo

import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.WindowManager
import android.widget.FrameLayout
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.ui.PlayerView
import com.google.android.exoplayer2.video.VideoSize
import com.google.firebase.firestore.EventListener
import kotlin.math.min

class MainActivity : AppCompatActivity() {

    private lateinit var playerView: PlayerView
    private lateinit var playerView1: PlayerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val displayMetrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(displayMetrics)
        val widthPixels = displayMetrics.widthPixels
        val heightPixels = displayMetrics.heightPixels
        var videoWidth = 0
        var videoHeight =0
        var videoWidth1 = 0
        var videoHeight1 = 0
        var ratio=0
        var ratio1=0
        //println(widthPixels.toString()+"--"+heightPixels.toString())

        playerView = findViewById(R.id.player_view)
        playerView1 = findViewById(R.id.player_view1)
        val mediaItem = MediaItem.fromUri(Uri.parse(URL))

        val player1 = ExoPlayer.Builder(this).build()
        playerView1.player = player1

        val player = ExoPlayer.Builder(this).build()
        playerView.player = player

        playerSetter(player, mediaItem)
        playerSetter(player1, mediaItem)


        player.addListener(object :Player.Listener{
            override fun onVideoSizeChanged(videoSize: VideoSize) {
                super.onVideoSizeChanged(videoSize)

                 videoWidth = videoSize.width
                 videoHeight = videoSize.height
                 ratio = min(widthPixels / videoWidth!!, heightPixels / videoHeight!!)


            }
        })
        val newWidth = (videoWidth * ratio)
        val newHeight = (videoHeight * ratio)
       // setCoordinate(playerView, 0, 0, newWidth, newHeight)

        player1.addListener(object :Player.Listener{
            override fun onVideoSizeChanged(videoSize: VideoSize) {
                super.onVideoSizeChanged(videoSize)

                 videoWidth1 = videoSize.width
                 videoHeight1 = videoSize.height
                 ratio1 = min(widthPixels / videoWidth1!!, heightPixels / videoHeight1!!)

            }
        })
        val newWidth1 = (videoWidth1 * ratio1)
        val newHeight1 = (videoHeight1 * ratio1)
        setCoordinate(playerView1, 50, 50, newWidth1, newHeight1)





        /*    setCoordinate(playerView,0,500,500,500)
            setCoordinate(playerView1,0,700,500,500)*/




    }

    fun setCoordinate(player: PlayerView, x: Int, y: Int, width: Int, height: Int) {
        val layoutParams = player.layoutParams as FrameLayout.LayoutParams
        layoutParams.width = width
        layoutParams.height = height
        layoutParams.leftMargin = x
        layoutParams.topMargin = y
        player.layoutParams = layoutParams
    }

    fun playerSetter(player: ExoPlayer, mediaItem: MediaItem) {
        player.setMediaItem(mediaItem)
        player.prepare()
        player.play()
    }



    companion object {
        const val URL =
            "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"
    }
}

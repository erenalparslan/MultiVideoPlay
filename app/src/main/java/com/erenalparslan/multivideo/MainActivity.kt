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
    private lateinit var playerView2: PlayerView
    private lateinit var playerView3: PlayerView
    private lateinit var playerView4: PlayerView
    private lateinit var playerView5: PlayerView
    private lateinit var playerView6: PlayerView
    private lateinit var playerView7: PlayerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        playerView = findViewById(R.id.player_view)
        playerView1 = findViewById(R.id.player_view1)
        playerView2 = findViewById(R.id.player_view2)
        playerView3 = findViewById(R.id.player_view3)
        playerView4 = findViewById(R.id.player_view4)
        playerView5 = findViewById(R.id.player_view5)
        playerView6 = findViewById(R.id.player_view6)
        playerView7 = findViewById(R.id.player_view7)
        val mediaItem = MediaItem.fromUri(Uri.parse(URL))

        val player = ExoPlayer.Builder(this).build()
        playerView.player = player
        val player1 = ExoPlayer.Builder(this).build()
        playerView1.player = player1
        val player2 = ExoPlayer.Builder(this).build()
        val player3 = ExoPlayer.Builder(this).build()
        val player4 = ExoPlayer.Builder(this).build()
        val player5 = ExoPlayer.Builder(this).build()
        val player6 = ExoPlayer.Builder(this).build()
        val player7 = ExoPlayer.Builder(this).build()

        playerView2.player = player2
        playerView3.player = player3
        playerView4.player = player4
        playerView5.player = player5
        playerView6.player = player6
        playerView7.player = player7


        playerSetter(player, mediaItem)
        playerSetter(player1, mediaItem)
        playerSetter(player2, mediaItem)
        playerSetter(player3, mediaItem)
        playerSetter(player4, mediaItem)
        playerSetter(player5, mediaItem)
        playerSetter(player6, mediaItem)
        playerSetter(player7, mediaItem)



           setCoordinate(playerView, 0, 0, 50, 50)

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

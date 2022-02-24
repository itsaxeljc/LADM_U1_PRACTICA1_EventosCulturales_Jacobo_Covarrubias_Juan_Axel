package mx.tecnm.tepic.ladm_u1_practica1_eventosculturales

import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.VideoView
import androidx.appcompat.app.AlertDialog
import mx.tecnm.tepic.ladm_u1_practica1_eventosculturales.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    protected lateinit var video_back: VideoView
    protected lateinit var mMediaPlayer: MediaPlayer
    protected var mCurrentVideoPosition: Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.comenzar.setOnClickListener{
            iniciarsesion()
        }

        video_back = findViewById(R.id.video)

        val uri = Uri.parse("android.resource://"
                            + packageName
                            + "/"
                            + R.raw.fondo)
        video_back.setVideoURI(uri)

        video_back.start()

        video_back.setOnPreparedListener{ mp ->
            mMediaPlayer = mp
            mMediaPlayer.isLooping = true

            if(mCurrentVideoPosition != 0){
                mMediaPlayer.seekTo(mCurrentVideoPosition)
                mMediaPlayer.start()
            }
        }

    }

    override fun onPause() {
        super.onPause()

        mCurrentVideoPosition = mMediaPlayer.currentPosition
        video_back.pause()
    }

    override fun onResume() {
        super.onResume()

        video_back.start()
    }

    override fun onDestroy() {
        super.onDestroy()

        mMediaPlayer.release()
    }

    private fun iniciarsesion(){
        try {
            AlertDialog.Builder(this).setMessage("Bienvenido ${binding.mail.text}").show()
            callFirst()
        }catch (e:Exception){
            AlertDialog.Builder(this).setMessage(e.message).show()
        }
    }

    fun callFirst(){
        val home = Intent(this, MainActivity2::class.java)
        startActivity(home)
    }

}
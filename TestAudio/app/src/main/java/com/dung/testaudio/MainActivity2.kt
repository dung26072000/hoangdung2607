package com.dung.testaudio

import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.widget.Button
import android.widget.SeekBar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.dung.testaudio.model.TestAudio
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity2 : AppCompatActivity() {
    lateinit var topAppBar: Toolbar
    private var mediaPlayer: MediaPlayer? = null
    private lateinit var seekBar: SeekBar
    private lateinit var buttonPlay:FloatingActionButton
    private lateinit var buttonPause: FloatingActionButton
    private lateinit var buttonStop: FloatingActionButton


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
         buttonPlay = findViewById(R.id.ButtonPlay)
         buttonPause= findViewById(R.id.ButtonPause)
         buttonStop = findViewById(R.id.ButtonStop)
         seekBar = findViewById<SeekBar>(R.id.SeeBar)
         topAppBar = findViewById(R.id.toolbar_title)
         val textTitle = findViewById<Button>(R.id.tv_title)

        val testAudio = intent.getParcelableExtra<TestAudio>("testAudio")
        val currentSong = mutableListOf(testAudio!!.audio)

        controlSong(currentSong[0])

        topAppBar.setNavigationOnClickListener {
            onBackPressed()
            return@setNavigationOnClickListener
        }
        textTitle.text=testAudio.textAudio

    }
    private fun controlSong(id:Int){
        buttonPlay.setOnClickListener{
            if(mediaPlayer== null){
                mediaPlayer = MediaPlayer.create(this,id)
            }
            mediaPlayer?.start()
            initSeeBar()
        }
        buttonPause.setOnClickListener{
            if(mediaPlayer!== null) mediaPlayer?.pause()
        }
        buttonStop.setOnClickListener{
            if(mediaPlayer!== null){
                mediaPlayer?.stop()
                mediaPlayer?.reset()
                mediaPlayer?.release()
                mediaPlayer=null
            }
        }
        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(p0: SeekBar?, progress: Int, user: Boolean) {
                if (user) mediaPlayer?.seekTo(progress)
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {

            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
            }
        })
    }

    private fun initSeeBar() {
        seekBar.max=mediaPlayer!!.duration

        val handler = Handler()
        handler.postDelayed(object : Runnable  {
            override fun run(){
                try {
                    seekBar.progress = mediaPlayer!!.currentPosition
                    handler.postDelayed(this,1000)
                }catch (e:Exception){
                    seekBar.progress = 0
                }
            }
        },0)
    }
}
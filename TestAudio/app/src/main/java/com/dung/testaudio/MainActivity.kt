package com.dung.testaudio

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dung.testaudio.adapter.TestAudioAdapter
import com.dung.testaudio.model.TestAudio
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var audioList : ArrayList<TestAudio>
    private lateinit var testAudioAdapter: TestAudioAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView = findViewById(R.id.rcv_audio)


        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)

        rcv_audio.addItemDecoration(DividerItemDecoration(this@MainActivity, LinearLayoutManager.VERTICAL))
        audioList= ArrayList()
        audioList.add(TestAudio("keep on movin",R.raw.keep_on_movin))
        audioList.add(TestAudio("Yes and No at the Same Time",R.raw.yes_and_no_at_the_same_time))
        audioList.add(TestAudio("Next Steps",R.raw.next_steps))
        audioList.add(TestAudio("Will 2 Pwr",R.raw.will_2_pwr))
        audioList.add(TestAudio("Insta Beat Vixens",R.raw.insta_beat_vixens))
        audioList.add(TestAudio("Sharp Edges",R.raw.sharp_edges))
        testAudioAdapter = TestAudioAdapter(audioList)
        recyclerView.adapter = testAudioAdapter
        testAudioAdapter.onItemClick = {
            val intent = Intent(this,MainActivity2::class.java)
            intent.putExtra("testAudio",it)
            startActivity(intent)
        }
    }
}
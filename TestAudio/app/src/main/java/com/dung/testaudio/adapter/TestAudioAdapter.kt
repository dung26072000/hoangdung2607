package com.dung.testaudio.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dung.testaudio.R
import com.dung.testaudio.model.TestAudio


class TestAudioAdapter(private val AudioList:ArrayList<TestAudio>) : RecyclerView.Adapter<TestAudioAdapter.TestAudioViewHolder>(){

    var onItemClick: ((TestAudio) -> Unit)? = null

    class TestAudioViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val textAudio: TextView = itemView.findViewById(R.id.tv_textAudio)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TestAudioViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.iteam_activity , parent,false)
        return TestAudioViewHolder(view)    }

    override fun onBindViewHolder(holder: TestAudioViewHolder, position: Int) {
        val testAudio = AudioList[position]
        holder.textAudio.text= testAudio.textAudio
        holder.itemView.setOnClickListener {
            onItemClick?.invoke(testAudio)
        }
            }

    override fun getItemCount(): Int {
        return AudioList.size
    }
}
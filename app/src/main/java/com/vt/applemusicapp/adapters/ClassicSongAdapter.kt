package com.vt.applemusicapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.vt.applemusicapp.R
import com.vt.applemusicapp.model.classic.Classic
import com.vt.applemusicapp.model.classic.ClassicSong

class ClassicSongAdapter(
    private val classicSongList: MutableList<ClassicSong> = mutableListOf()
) : RecyclerView.Adapter<ClassicSongViewHolder>() {

    fun updateClassicSong(classicSong: Classic){
        classicSongList.clear()
        classicSongList.addAll(classicSong.classicSongs)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClassicSongViewHolder {
        val classicSongView = LayoutInflater.from(parent.context).inflate(
            R.layout.song_item,
            parent,
            false
        )
        return ClassicSongViewHolder(classicSongView)
    }

    override fun onBindViewHolder(holder: ClassicSongViewHolder, position: Int) {
        val classicSong = classicSongList[position]

        holder.artistName.text = classicSong.artistName
        holder.songName.text = classicSong.trackName
        holder.songPrice.text = "$" + classicSong.trackPrice.toString()

        Picasso
            .get()
            .load(classicSong.artworkUrl100)
            .into(holder.artwork)
    }

    override fun getItemCount(): Int = classicSongList.size

}

class ClassicSongViewHolder( itemView: View) : RecyclerView.ViewHolder(itemView){
    val songName: TextView = itemView.findViewById(R.id.songName_tv)
    val artistName: TextView = itemView.findViewById(R.id.artistName_tv)
    val songPrice: TextView = itemView.findViewById(R.id.price_tv)
    val artwork: ImageView = itemView.findViewById(R.id.artwork_tv)
}
package com.vt.applemusicapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.vt.applemusicapp.R
import com.vt.applemusicapp.model.pop.Pop
import com.vt.applemusicapp.model.pop.PopSong

class PopSongAdapter(
    private val popSongList: MutableList<PopSong> = mutableListOf()
) : RecyclerView.Adapter<PopSongViewHolder>() {

    fun updatePopSong(popSong: Pop){
        popSongList.clear()
        popSongList.addAll(popSong.popSongs)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopSongViewHolder {
        val popSongView = LayoutInflater.from(parent.context).inflate(
            R.layout.song_item,
            parent,
            false
        )
        return PopSongViewHolder(popSongView)
    }

    override fun onBindViewHolder(holder: PopSongViewHolder, position: Int) {
        val popSong = popSongList[position]

        holder.artistName.text = popSong.artistName
        holder.songName.text = popSong.trackName
        holder.songPrice.text = "$" + popSong.trackPrice.toString()

        Picasso
            .get()
            .load(popSong.artworkUrl100)
            .into(holder.artwork)
    }

    override fun getItemCount(): Int = popSongList.size

}

class PopSongViewHolder( itemView: View) : RecyclerView.ViewHolder(itemView){
    val songName: TextView = itemView.findViewById(R.id.songName_tv)
    val artistName: TextView = itemView.findViewById(R.id.artistName_tv)
    val songPrice: TextView = itemView.findViewById(R.id.price_tv)
    val artwork: ImageView = itemView.findViewById(R.id.artwork_tv)
}
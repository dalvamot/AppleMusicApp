package com.vt.applemusicapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.vt.applemusicapp.R
import com.vt.applemusicapp.model.Rock.Rock
import com.vt.applemusicapp.model.Rock.RockSong

class RockSongAdapter(
    private val rockSongList: MutableList<RockSong> = mutableListOf()
) : RecyclerView.Adapter<RockSongViewHolder>() {

    fun updateRockSong(rockSong: Rock){
        rockSongList.clear()
        rockSongList.addAll(rockSong.rockSongs)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RockSongViewHolder {
        val rockSongView = LayoutInflater.from(parent.context).inflate(
            R.layout.song_item,
            parent,
            false
        )
        return RockSongViewHolder(rockSongView)
    }

    override fun onBindViewHolder(holder: RockSongViewHolder, position: Int) {
        val rockSong = rockSongList[position]

        holder.artistName.text = rockSong.artistName
        holder.songName.text = rockSong.trackName
        holder.songPrice.text = "$" + rockSong.trackPrice.toString()

        Picasso
            .get()
            .load(rockSong.artworkUrl100)
            .into(holder.artwork)
    }

    override fun getItemCount(): Int = rockSongList.size

}

class RockSongViewHolder ( itemView: View) : RecyclerView.ViewHolder(itemView){
    val songName: TextView = itemView.findViewById(R.id.songName_tv)
    val artistName: TextView = itemView.findViewById(R.id.artistName_tv)
    val songPrice: TextView = itemView.findViewById(R.id.price_tv)
    val artwork: ImageView = itemView.findViewById(R.id.artwork_tv)
}
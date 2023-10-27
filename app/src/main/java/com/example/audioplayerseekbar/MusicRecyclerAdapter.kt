package com.example.audioplayerseekbar

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Recycler
import com.example.audioplayerseekbar.databinding.RowPlaylistBinding

class MusicRecyclerAdapter(val context: Context,val songList:ArrayList<MusicModel>):RecyclerView.Adapter<MusicRecyclerAdapter.ViewHolder>(){
class ViewHolder(val binding:RowPlaylistBinding):RecyclerView.ViewHolder(binding.root){

}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(RowPlaylistBinding.inflate(LayoutInflater.from(context),parent,false))
    }

    override fun getItemCount(): Int {
        return songList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.setOnClickListener{
            (context as MainActivity).playMusic(songList[position])
        }
        holder.binding.txtSingerName.text=songList[position].singerName
        holder.binding.txtSongName.text=songList[position].songName
        holder.binding.imgSong.setImageResource(songList[position].song_thumb)
    }
}
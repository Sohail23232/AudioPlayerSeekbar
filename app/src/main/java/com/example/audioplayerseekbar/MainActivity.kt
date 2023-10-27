package com.example.audioplayerseekbar

import android.content.Intent
import android.media.AudioManager
import android.media.MediaPlayer
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.audioplayerseekbar.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var mp:MediaPlayer
    var currentPlayIndex=0
    var isPlaying=true
    companion object{
        val musicList=ArrayList<MusicModel>().apply {
            add(MusicModel(0,"Hotline Bling","Drake",R.raw.hotline_bling_drake,R.drawable.hotline_bling_drake))
            add(MusicModel(1,"One Dance","Drake",R.raw.one_dance_drake,R.drawable.one_dance_thumb))
            add(MusicModel(2,"Mia-Drake","Drake",R.raw.mia_drake,R.drawable.mia_thumb))
            add(MusicModel(3,"God Plan","Drake",R.raw.god_plan_drake,R.drawable.god_plan_thumb))
            add(MusicModel(4,"Future Life is Good ","Drake",R.raw.future_life_is_good_drake,R.drawable.life_is_good_thumb))
            add(MusicModel(5,"Rich Daddy","Drake",R.raw.rich_babby_drake,R.drawable.rich_babby_thumb))
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.recView.layoutManager=LinearLayoutManager(this@MainActivity)
        binding.recView.adapter=MusicRecyclerAdapter(this@MainActivity,musicList)
        mp= MediaPlayer()
        mp.setAudioStreamType(AudioManager.STREAM_MUSIC)
        mp.setDataSource(resources.openRawResourceFd(musicList[0].song))
        mp.prepare()
        mp.start()
        binding.btnPlay.setOnClickListener {
            if (isPlaying){
                binding.btnPlay.setImageResource(R.drawable.baseline_play_arrow_24)
                isPlaying=false
                mp.pause()
            }else{
                binding.btnPlay.setImageResource(R.drawable.baseline_pause_24)
                isPlaying=true
                mp.start()
            }

        }
        binding.btnNextSong.setOnClickListener {
            if (currentPlayIndex==musicList.size-1)
            {
                playMusic(musicList[0])
                currentPlayIndex=0
            }
            else {
                val nextSong = currentPlayIndex + 1
                playMusic(musicList[nextSong])
            }
        }
        binding.btnPreviousSong.setOnClickListener {
            if (currentPlayIndex>0){
                val prevSong=currentPlayIndex-1
                playMusic(musicList[prevSong])
            }
            else{
                playMusic(musicList[0])
                currentPlayIndex=0
            }
        }
    }


    fun playMusic(model:MusicModel){
currentPlayIndex=model.id
        mp.reset()
        mp.setDataSource(resources.openRawResourceFd(model.song))
        mp.prepare()
        mp.start()
        binding.txtSingerName.text=model.singerName
        binding.txtSongName.text=model.songName
        binding.imgSong.setImageResource(model.song_thumb)
    }

    override fun onDestroy() {
            super.onDestroy()
       mp.stop()
        mp.release()
    }

    override fun onBackPressed() {
        super.onBackPressed()
       finish()
    }
}
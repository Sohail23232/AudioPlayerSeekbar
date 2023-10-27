package com.example.audioplayerseekbar

import android.media.AudioManager
import android.media.MediaPlayer
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore.Audio.Media
import android.util.Log
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import com.example.audioplayerseekbar.databinding.ActivityPlayingBinding
import java.lang.Exception

class PlayingActivity : AppCompatActivity() {
    lateinit var binding: ActivityPlayingBinding
    lateinit var mp:MediaPlayer
    var isPlaying=true
    var index=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityPlayingBinding.inflate(layoutInflater)
        setContentView(binding.root)
         index=intent.getIntExtra("index",0)
        mp= MediaPlayer()
        mp.setDataSource(resources.openRawResourceFd(MainActivity.musicList[index-1].song))
        binding.imgSong.setImageResource(MainActivity.musicList[index-1].song_thumb)
        binding.txtSingerName.text=MainActivity.musicList[index-1].singerName
        binding.txtSongName.text=MainActivity.musicList[index-1].songName
        mp.setAudioStreamType(AudioManager.STREAM_MUSIC)

val animations=AnimationUtils.loadAnimation(this,R.anim.rotate_music)
        binding.btnPlay.animation=animations
        animations.start()

        try {
            mp.prepare()
            mp.start()


        }
        catch (e:Exception){
            e.printStackTrace()
            Log.d("failed",e.message.toString())
        }

        binding.btnPlay.setOnClickListener{
            if (isPlaying){
                binding.btnPlay.setImageResource(R.drawable.baseline_play_arrow_24)
                mp.pause()
            }else{
                binding.btnPlay.setImageResource(R.drawable.baseline_pause_24)
                mp.start()
            }
            isPlaying = !isPlaying
        }
    }

    override fun onDestroy() {
        mp.release()
        super.onDestroy()
    }
}
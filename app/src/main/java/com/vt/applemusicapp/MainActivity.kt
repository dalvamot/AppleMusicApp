package com.vt.applemusicapp

import android.content.Context
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.android.material.tabs.TabLayoutMediator
import com.vt.applemusicapp.adapters.FragmentAdapter
import com.vt.applemusicapp.databinding.ActivityMainBinding
import com.vt.applemusicapp.model.Rock.Rock
import com.vt.applemusicapp.rest.Retrofit
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val  networkManager by lazy {
        getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.musicContainer.adapter = FragmentAdapter(supportFragmentManager, lifecycle)

        TabLayoutMediator(binding.musicMenu, binding.musicContainer) { tab, position ->
            when(position){
                0 -> {
                    tab.text = "Rock"
                    tab.icon = getDrawable(R.drawable.ic_launcher_foreground)
                }
                1 ->{
                    tab.text = "Classic"
                    tab.icon = getDrawable(R.drawable.ic_launcher_foreground)
                }
                2 -> {
                    tab.text = "Pop"
                    tab.icon = getDrawable(R.drawable.ic_launcher_foreground)
                }
            }
        }.attach()
        val activeNetwork = networkManager.activeNetworkInfo

        activeNetwork?.let { myNetwork ->
            if(myNetwork.isConnected){
                Retrofit.getNetworkApi().getRockSongs()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                        { songs -> handleSuccess(songs)},
                        { error -> handleError(error)}
                    )
            }else{
                Toast.makeText(baseContext, "Connectivity issues", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun handleSuccess(songs: Rock) {
        //rockSongAdapter.updateRockSong(songs)
    }

    private fun handleError(error: Throwable) {
        Toast.makeText(baseContext, error.localizedMessage, Toast.LENGTH_LONG).show()
    }
}
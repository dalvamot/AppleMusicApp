package com.vt.applemusicapp.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.vt.applemusicapp.adapters.RockSongAdapter
import com.vt.applemusicapp.databinding.FragmentRockBinding
import com.vt.applemusicapp.model.Rock.Rock
import com.vt.applemusicapp.presenters.IRockView
import com.vt.applemusicapp.presenters.RockPresenter

class RockFragment : Fragment(), IRockView {

    private val presenter: RockPresenter by lazy {
        RockPresenter(this)
    }

    private lateinit var binding: FragmentRockBinding
    private val rockSongAdapter = RockSongAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRockBinding.inflate(inflater, container, false)
        binding.rockSongsRecycler.apply {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            adapter = rockSongAdapter
        }
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        presenter.getRockSongsFromServer()
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            RockFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }

    override fun rockSongsUpdate(rockSong: Rock) {
        if(rockSong != null){
            rockSongAdapter.updateRockSong(rockSong)
        }
    }

    override fun onErrorData(error: Throwable) {
        Toast.makeText(requireContext(), error.localizedMessage, Toast.LENGTH_LONG).show()
    }
}
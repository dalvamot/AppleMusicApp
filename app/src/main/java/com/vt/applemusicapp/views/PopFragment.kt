package com.vt.applemusicapp.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.vt.applemusicapp.adapters.PopSongAdapter
import com.vt.applemusicapp.databinding.FragmentPopBinding
import com.vt.applemusicapp.model.pop.Pop
import com.vt.applemusicapp.presenters.IPopView
import com.vt.applemusicapp.presenters.PopPresenter

class PopFragment : Fragment(), IPopView {

    private val presenter: PopPresenter by lazy {
        PopPresenter(this)
    }

    private lateinit var binding: FragmentPopBinding
    private val popSongAdapter = PopSongAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPopBinding.inflate(inflater, container, false)
        binding.popSongsRecycler.apply {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            adapter = popSongAdapter
        }
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        presenter.getPopSongsFromServer()
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            PopFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }

    override fun popSongsUpdate(popSong: Pop) {
        if(popSong != null){
            popSongAdapter.updatePopSong(popSong)
        }
    }

    override fun onErrorData(error: Throwable) {
        Toast.makeText(requireContext(), error.localizedMessage, Toast.LENGTH_LONG).show()
    }
}
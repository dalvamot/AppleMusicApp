package com.vt.applemusicapp.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.vt.applemusicapp.adapters.ClassicSongAdapter
import com.vt.applemusicapp.databinding.FragmentClassicBinding
import com.vt.applemusicapp.model.classic.Classic
import com.vt.applemusicapp.presenters.ClassicPresenter
import com.vt.applemusicapp.presenters.IClassicView

class ClassicFragment : Fragment(), IClassicView {

    private val presenter: ClassicPresenter by lazy {
        ClassicPresenter(this)
    }

    private lateinit var binding: FragmentClassicBinding
    private val classicSongAdapter = ClassicSongAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentClassicBinding.inflate(inflater, container, false)
        binding.classicSongsRecycler.apply {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            adapter = classicSongAdapter
        }
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        presenter.getClassicSongsFromServer()
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            ClassicFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }

    override fun classicSongsUpdate(classicSong: Classic) {
        if(classicSong != null){
            classicSongAdapter.updateClassicSong(classicSong)
        }    }

    override fun onErrorData(error: Throwable) {
        Toast.makeText(requireContext(), error.localizedMessage, Toast.LENGTH_LONG).show()
    }
}
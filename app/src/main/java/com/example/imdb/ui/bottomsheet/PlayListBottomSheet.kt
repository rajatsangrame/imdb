package com.example.imdb.ui.bottomsheet

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.imdb.databinding.FragmentBottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class PlayListBottomSheet private constructor() : BottomSheetDialogFragment() {

    private var callback: OptionCLickListener? = null
    private lateinit var binding: FragmentBottomSheetBinding
    private lateinit var playlistAdapter: PlaylistAdapter
    private lateinit var playlist: List<String>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBottomSheetBinding
            .inflate(LayoutInflater.from(requireContext()), container, false)
        return binding.root
    }


    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)
        setup()
        setupListeners()
    }

    override fun onDestroy() {
        super.onDestroy()
        callback = null
    }

    private fun setup() {
        binding.rvPlayList.layoutManager = LinearLayoutManager(requireContext())
        playlistAdapter = PlaylistAdapter(playlist) {
            callback?.save(it)
        }
        binding.rvPlayList.adapter = playlistAdapter
        binding.btnNewPlaylist.setOnClickListener {
            Log.d(TAG, "setup: btnNewPlaylist")
        }
    }


    private fun setupListeners() {

    }

    interface OptionCLickListener {
        fun save(playlist: String)
        fun new(playlist: String)
    }


    companion object {
        const val TAG = "PlayListBottomSheet"

        @JvmStatic
        fun newInstance(
            callback: OptionCLickListener,
            playlist: List<String>
        ): PlayListBottomSheet {
            return PlayListBottomSheet().apply {
                this.callback = callback
                this.playlist = playlist
            }
        }
    }
}
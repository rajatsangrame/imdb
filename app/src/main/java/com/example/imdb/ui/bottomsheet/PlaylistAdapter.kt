package com.example.imdb.ui.bottomsheet

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.imdb.databinding.PlaylistItemBinding


class PlaylistAdapter(
    private var playList: List<String> = ArrayList(),
    private val listener: (String) -> Unit
) :
    RecyclerView.Adapter<PlaylistAdapter.ViewHolder>() {

    fun setList(playList: List<String>) {
        this.playList = playList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaylistAdapter.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = PlaylistItemBinding.inflate(
            layoutInflater,
            parent, false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PlaylistAdapter.ViewHolder, position: Int) {
        holder.binding.tvPlaylist.text = playList[position]
    }

    override fun getItemCount(): Int {
        return playList.size
    }

    inner class ViewHolder(val binding: PlaylistItemBinding) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.tvPlaylist.setOnClickListener {
                listener(playList[adapterPosition])
            }
        }
    }

}

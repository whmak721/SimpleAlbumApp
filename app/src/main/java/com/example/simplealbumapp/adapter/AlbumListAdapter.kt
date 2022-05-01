package com.example.simplealbumapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.simplealbumapp.R
import com.example.simplealbumapp.util.SharedPreference
import com.example.simplealbumapp.databinding.ItemAlbumBinding
import com.example.simplealbumapp.formatDate
import com.example.simplealbumapp.model.Album
import com.google.android.material.snackbar.Snackbar

class AlbumListAdapter(private var data: ArrayList<Album>) :
    RecyclerView.Adapter<AlbumListAdapter.AlbumViewHolder>() {
    private lateinit var mContext: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumViewHolder {
        val binding = ItemAlbumBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        mContext = parent.context
        return AlbumViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) {
        holder.bind(data[position], position)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    private fun saveBookmark(position: Int) {
        SharedPreference(context = mContext).saveBookmark(
            SharedPreference.DATA,
            data[position].collectionId.toString()
        )
    }

    private fun removeBookmark(position: Int) {
        SharedPreference(context = mContext).removeBookmark(
            SharedPreference.DATA,
            data[position].collectionId.toString()
        )
    }

    inner class AlbumViewHolder(private val binding: ItemAlbumBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(album: Album, position: Int) {
            binding.apply {
                tvExplicitness.isVisible = album.isExplicit
                tvAlbumName.text = album.collectionName
                tvArtistName.text = album.artistName
                tvReleaseDate.text = mContext.getString(R.string.release_date).replace("{R1}", album.releaseDate.formatDate())
                tvPrice.text = album.formattedPrice
                Glide.with(mContext).load(album.artworkUrl100)
                    .into(this.ivAlbumCover)
                ivBookmark.apply {
                    isSelected = album.isBookmarked(mContext)

                    setOnClickListener {
                        if (!this.isSelected) {
                            saveBookmark(position)
                            Snackbar.make(this, context.getString(R.string.added_to_bookmark), Snackbar.LENGTH_SHORT).show();
                        } else {
                            removeBookmark(position)
                            Snackbar.make(this, context.getString(R.string.remove_from_bookmark), Snackbar.LENGTH_SHORT).show();
                        }
                        isSelected = !isSelected
                    }
                }
            }
        }
    }
}
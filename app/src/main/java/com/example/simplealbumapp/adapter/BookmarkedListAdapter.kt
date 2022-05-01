package com.example.simplealbumapp.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.simplealbumapp.R
import com.example.simplealbumapp.util.SharedPreference
import com.example.simplealbumapp.databinding.ItemBookmarkBinding
import com.example.simplealbumapp.formatDate
import com.example.simplealbumapp.model.Album
import com.google.android.material.snackbar.Snackbar

class BookmarkedListAdapter(private var data: ArrayList<Album>) :
    RecyclerView.Adapter<BookmarkedListAdapter.AlbumViewHolder>() {
    private lateinit var mContext: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumViewHolder {
        val binding =
            ItemBookmarkBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        mContext = parent.context
        return AlbumViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) {
        holder.bind(data[position], position)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    private fun removeItem(position: Int) {
        Log.e("allen debug removeItem position: ", position.toString())
        Log.e("allen debug removeItem data.size", data.size.toString())

        SharedPreference(context = mContext).removeBookmark(
            SharedPreference.DATA,
            data[position].collectionId.toString()
        )

        data.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, data.size);
    }

    inner class AlbumViewHolder(private val binding: ItemBookmarkBinding) :
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

                ivRemove.setOnClickListener {
                    removeItem(position)
                    Snackbar.make(it, "removeBookmark", Snackbar.LENGTH_LONG).show()
                }
            }

        }
    }
}
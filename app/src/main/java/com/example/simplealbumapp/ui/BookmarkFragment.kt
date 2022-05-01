package com.example.simplealbumapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.simplealbumapp.util.SharedPreference
import com.example.simplealbumapp.viewmodel.ViewModel
import com.example.simplealbumapp.adapter.BookmarkedListAdapter
import com.example.simplealbumapp.databinding.FragmentBookmarkBinding
import com.example.simplealbumapp.model.Album

class BookmarkFragment : Fragment() {

    private var _binding: FragmentBookmarkBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: ViewModel
    private lateinit var bookmarkedListAdapter: BookmarkedListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBookmarkBinding.inflate(inflater, container, false)

        viewModel = ViewModelProvider(requireActivity())[ViewModel::class.java]

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.albumDataList.observe(requireActivity()) {
            if (!this::bookmarkedListAdapter.isInitialized) {
                val bookmarkedList =
                    SharedPreference(requireContext()).readBookmark(SharedPreference.DATA)

                val data = arrayListOf<Album>()
                it.results.forEach { album ->
                    bookmarkedList.forEach { bookmarked ->
                        if (album.collectionId.toString() == bookmarked)
                            data.add(album)
                    }
                }

                binding.rvBookmarkList.addItemDecoration(
                    DividerItemDecoration(
                        requireContext(),
                        LinearLayoutManager.VERTICAL
                    )
                )

                bookmarkedListAdapter = BookmarkedListAdapter(data)
                binding.rvBookmarkList.apply {
                    layoutManager = LinearLayoutManager(activity)
                    adapter = bookmarkedListAdapter
                }
            }
        }
    }
}
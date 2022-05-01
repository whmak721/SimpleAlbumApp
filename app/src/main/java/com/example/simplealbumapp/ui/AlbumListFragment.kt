package com.example.simplealbumapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.simplealbumapp.viewmodel.ViewModel
import com.example.simplealbumapp.adapter.AlbumListAdapter
import com.example.simplealbumapp.databinding.FragmentHomeBinding

class AlbumListFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: ViewModel
    private lateinit var albumListAdapter: AlbumListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        if (_binding == null) {
            _binding = FragmentHomeBinding.inflate(inflater, container, false)
        }

        viewModel = ViewModelProvider(requireActivity())[ViewModel::class.java]

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getAlbumList()
        viewModel.albumDataList.observe(requireActivity()) {
            if (!this::albumListAdapter.isInitialized) {
                albumListAdapter = AlbumListAdapter(it.results)
                binding.rvAlbumList.apply {
                    layoutManager = LinearLayoutManager(activity)
                    adapter = albumListAdapter
                }
            }
        }

//        val pref = requireContext().getSharedPreferences(SharedPreference.BOOKMARKED, MODE_PRIVATE)
//        pref.registerOnSharedPreferenceChangeListener { sharedPreferences, key ->
//            albumListAdapter.notifyDataSetChanged()
//        }
    }
}
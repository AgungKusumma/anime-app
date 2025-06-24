package com.agungkusuma.featurehome.presentation.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.agungkusuma.core.domain.model.Anime
import com.agungkusuma.common.utils.ActionState
import com.agungkusuma.featurehome.databinding.FragmentHomeBinding
import com.agungkusuma.featurehome.presentation.adapter.AnimeAdapter
import com.agungkusuma.featurehome.presentation.mapper.toUi
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val animeAdapter: AnimeAdapter by lazy {
        AnimeAdapter(
            onItemClick = {
                Log.d("HomeFragment", "id : ${it.id}")
            }
        )
    }

    private val viewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        observeAnime()
        viewModel.fetchAnimeList()
    }

    private fun setupRecyclerView() {
        with(binding.rvAnime) {
            adapter = animeAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    private fun observeAnime() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.animeState.collectLatest { state ->
                when (state) {
                    is ActionState.Loading -> {
                    }

                    is ActionState.Success -> {
                        val animeList = state.data as List<Anime>
                        val animeData = animeList.map { it.toUi() }
                        animeAdapter.submitList(animeData)
                    }

                    is ActionState.Error -> {
                        Toast.makeText(requireContext(), state.data.toString(), Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

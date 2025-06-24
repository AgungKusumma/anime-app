package com.agungkusuma.featurehome.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.agungkusuma.common.R
import com.agungkusuma.common.navigation.FeaturesNavigation
import com.agungkusuma.common.utils.ActionState
import com.agungkusuma.common.utils.Constants
import com.agungkusuma.core.domain.model.Anime
import com.agungkusuma.featurehome.databinding.FragmentHomeBinding
import com.agungkusuma.featurehome.presentation.adapter.AnimeAdapter
import com.agungkusuma.featurehome.presentation.mapper.toUi
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : Fragment() {

    @Inject
    lateinit var featuresNavigation: FeaturesNavigation

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val animeAdapter: AnimeAdapter by lazy {
        AnimeAdapter(
            onItemClick = {
                featuresNavigation.openDetailPage(
                    bundleOf(Constants.KeyParam.KEY_DATA to it.id)
                )
            })
    }

    private val viewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
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
                        binding.loadingAnimation.playAnimation()
                        binding.loadingAnimation.visibility = View.VISIBLE
                    }

                    is ActionState.Success -> {
                        binding.loadingAnimation.cancelAnimation()
                        binding.loadingAnimation.visibility = View.GONE

                        val animeList = state.data as List<Anime>
                        val animeData = animeList.map { it.toUi() }
                        animeAdapter.submitList(animeData)
                    }

                    is ActionState.Error -> {
                        binding.loadingAnimation.cancelAnimation()
                        binding.loadingAnimation.visibility = View.GONE

                        binding.errorAnimation.playAnimation()
                        binding.errorAnimation.visibility = View.VISIBLE

                        Toast.makeText(
                            requireContext(), getString(R.string.error), Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.loadingAnimation.cancelAnimation()
        binding.errorAnimation.cancelAnimation()
        _binding = null
    }
}

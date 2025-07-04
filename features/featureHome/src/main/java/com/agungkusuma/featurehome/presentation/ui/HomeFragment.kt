package com.agungkusuma.featurehome.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.agungkusuma.common.R
import com.agungkusuma.common.adapter.AnimeAdapter
import com.agungkusuma.common.navigation.FeaturesNavigation
import com.agungkusuma.common.utils.ActionState
import com.agungkusuma.core.utils.Constants
import com.agungkusuma.core.domain.model.Anime
import com.agungkusuma.featurehome.databinding.FragmentHomeBinding
import com.agungkusuma.common.mapper.toUi
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private val featuresNavigation: FeaturesNavigation by inject()

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val animeAdapter: AnimeAdapter by lazy {
        AnimeAdapter(
            onItemClick = {
                featuresNavigation.openDetailPage(
                    bundleOf(Constants.KeyParam.KEY_DATA to it.id)
                )
            },
        )
    }

    private val viewModel: HomeViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupAction()
        setupRecyclerView()
        observeAnime()
        viewModel.fetchAnimeList()
    }

    private fun setupAction() = with(binding) {
        fabFav.setOnClickListener {
            featuresNavigation.openFavoritePage()
        }
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
        binding.rvAnime.adapter = null
        animeAdapter.submitList(null)
        _binding = null
    }
}

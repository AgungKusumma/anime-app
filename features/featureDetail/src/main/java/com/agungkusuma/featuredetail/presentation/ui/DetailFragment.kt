package com.agungkusuma.featuredetail.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.agungkusuma.common.R
import com.agungkusuma.common.navigation.FeaturesNavigation
import com.agungkusuma.common.utils.ActionState
import com.agungkusuma.core.domain.model.AnimeDetail
import com.agungkusuma.core.utils.Constants
import com.agungkusuma.featuredetail.databinding.FragmentDetailBinding
import com.agungkusuma.featuredetail.presentation.mapper.toUiModel
import com.agungkusuma.featuredetail.presentation.model.AnimeDetailUiModel
import com.bumptech.glide.Glide
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailFragment : Fragment() {

    private val featuresNavigation: FeaturesNavigation by inject()

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    private val viewModel: DetailViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val id = arguments?.getInt(Constants.KeyParam.KEY_DATA)

        setupAction()
        observeAnimeDetail()
        if (id != null) {
            viewModel.fetchAnimeDetail(id)
        }
    }

    private fun setupAction() = with(binding) {
        btnBack.setOnClickListener {
            featuresNavigation.navigateUp()
        }

        ivFavorite.setOnClickListener {
            viewModel.insertAnime()
            setStatusFavorite(true)
        }
    }

    private fun observeAnimeDetail() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.animeDetailState.collectLatest { state ->
                when (state) {
                    is ActionState.Loading -> {
                        binding.loadingAnimation.visibility = View.VISIBLE
                        binding.loadingAnimation.playAnimation()
                    }

                    is ActionState.Success -> {
                        binding.loadingAnimation.visibility = View.GONE
                        binding.loadingAnimation.cancelAnimation()

                        val detail = state.data as AnimeDetail
                        val dataDetail = detail.toUiModel()

                        setupDetailData(dataDetail)
                    }

                    is ActionState.Error -> {
                        binding.loadingAnimation.visibility = View.GONE
                        binding.loadingAnimation.cancelAnimation()

                        binding.errorAnimation.visibility = View.VISIBLE
                        binding.errorAnimation.playAnimation()

                        Toast.makeText(
                            requireContext(), getString(R.string.error), Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.insertState.collectLatest { state ->
                when (state) {
                    is ActionState.Success -> {
                        Toast.makeText(
                            requireContext(), getString(R.string.fav_added), Toast.LENGTH_SHORT
                        ).show()
                    }

                    is ActionState.Error -> {
                        Toast.makeText(
                            requireContext(), getString(R.string.fav_add_fail), Toast.LENGTH_SHORT
                        ).show()
                    }

                    else -> {}
                }
            }
        }
    }

    private fun setupDetailData(data: AnimeDetailUiModel) = with(binding) {
        Glide.with(requireContext()).load(data.imageUrl).into(binding.ivAnime)

        binding.tvTitle.text = data.title
        binding.tvTypeEpisodes.text = data.typeAndEpisodes
        binding.tvScore.text = "Score: ⭐ ${data.score}"
        binding.tvGenres.text = "Genres: ${data.genres}"
        binding.tvAired.text = "Aired: ${data.aired}"
        binding.tvSynopsis.text = data.synopsis
    }

    private fun setStatusFavorite(statusFavorite: Boolean) {
        if (statusFavorite) {
            binding.ivFavorite.setImageResource(R.drawable.ic_fav)
        } else {
            binding.ivFavorite.setImageResource(R.drawable.ic_fav_outline)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.loadingAnimation.cancelAnimation()
        binding.errorAnimation.cancelAnimation()
        _binding = null
    }
}

package com.agungkusuma.featuredetail.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.agungkusuma.common.R
import com.agungkusuma.common.navigation.FeaturesNavigation
import com.agungkusuma.common.utils.ActionState
import com.agungkusuma.common.utils.Constants
import com.agungkusuma.core.domain.model.AnimeDetail
import com.agungkusuma.featuredetail.databinding.FragmentDetailBinding
import com.agungkusuma.featuredetail.presentation.mapper.toUiModel
import com.agungkusuma.featuredetail.presentation.model.AnimeDetailUiModel
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class DetailFragment : Fragment() {

    @Inject
    lateinit var featuresNavigation: FeaturesNavigation

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    private val viewModel: DetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
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
    }

    private fun observeAnimeDetail() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.animeDetailState.collectLatest { state ->
                when (state) {
                    is ActionState.Loading -> {
                        // show loading if needed
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

                        Toast.makeText(requireContext(), getString(R.string.error), Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            }
        }
    }

    private fun setupDetailData(data: AnimeDetailUiModel) = with(binding) {
        Glide.with(requireContext())
            .load(data.imageUrl)
            .into(binding.ivAnime)

        binding.tvTitle.text = data.title
        binding.tvTypeEpisodes.text = data.typeAndEpisodes
        binding.tvScore.text = "Score: ⭐ ${data.score}"
        binding.tvGenres.text = "Genres: ${data.genres}"
        binding.tvAired.text = "Aired: ${data.aired}"
        binding.tvSynopsis.text = data.synopsis
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.loadingAnimation.cancelAnimation()
        binding.errorAnimation.cancelAnimation()
        _binding = null
    }
}

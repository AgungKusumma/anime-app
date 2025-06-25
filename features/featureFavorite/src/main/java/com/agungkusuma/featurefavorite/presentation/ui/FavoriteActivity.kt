package com.agungkusuma.featurefavorite.presentation.ui

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.agungkusuma.common.adapter.AnimeAdapter
import com.agungkusuma.common.model.AnimeUiModel
import com.agungkusuma.featurefavorite.R
import com.agungkusuma.featurefavorite.databinding.ActivityFavoriteBinding
import com.agungkusuma.featurefavorite.di.favoriteModule
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class FavoriteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFavoriteBinding

    private val viewModel: FavoriteViewModel by viewModel()

    private val animeAdapter: AnimeAdapter by lazy {
        AnimeAdapter(
            onItemClick = {
            },
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        loadKoinModules(favoriteModule)
        setupData()
        setupRecyclerView()
        setupAction()
    }

    private fun setupData() {
        val dummyAnime = listOf(
            AnimeUiModel(
                id = 1,
                title = "Naruto",
                score = 8.5.toString(),
                imageUrl = "https://cdn.myanimelist.net/images/anime/1141/142503.jpg",
                type = "a",
                aired = "s"
            ),
            AnimeUiModel(
                id = 1,
                title = "Naruto",
                score = 8.5.toString(),
                imageUrl = "https://cdn.myanimelist.net/images/anime/1141/142503.jpg",
                type = "a",
                aired = "s"
            ),
            AnimeUiModel(
                id = 1,
                title = "Naruto",
                score = 8.5.toString(),
                imageUrl = "https://cdn.myanimelist.net/images/anime/1141/142503.jpg",
                type = "a",
                aired = "s"
            ),
        )
        animeAdapter.submitList(dummyAnime)
    }

    private fun setupAction() {
        binding.btnBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
    }

    private fun setupRecyclerView() {
        with(binding.rvAnime) {
            adapter = animeAdapter
            layoutManager = LinearLayoutManager(this@FavoriteActivity)
        }
    }
}
package com.agungkusuma.featurefavorite.presentation.ui

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.agungkusuma.common.adapter.AnimeAdapter
import com.agungkusuma.common.mapper.toUi
import com.agungkusuma.featurefavorite.R
import com.agungkusuma.featurefavorite.databinding.ActivityFavoriteBinding
import com.agungkusuma.featurefavorite.di.favoriteModule
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class FavoriteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFavoriteBinding

    private val viewModel: FavoriteViewModel by viewModel()

    private val animeAdapter: AnimeAdapter by lazy {
        AnimeAdapter(
            onItemClick = {},
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
        setupRecyclerView()
        setupAction()

        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.favoriteAnime.collect { list ->
                    val dataFav = list.map { it.toUi() }
                    animeAdapter.submitList(dataFav)
                }
            }
        }
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
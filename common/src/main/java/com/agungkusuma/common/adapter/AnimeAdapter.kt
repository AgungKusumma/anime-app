package com.agungkusuma.common.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.agungkusuma.common.databinding.ItemAnimeBinding
import com.agungkusuma.common.model.AnimeUiModel
import com.bumptech.glide.Glide

class AnimeAdapter(
    private val onItemClick: (AnimeUiModel) -> Unit
) : ListAdapter<AnimeUiModel, AnimeAdapter.AnimeViewHolder>(DiffCallback) {

    inner class AnimeViewHolder(private val binding: ItemAnimeBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: AnimeUiModel) {
            binding.tvTitle.text = item.title
            binding.tvScore.text = "⭐ ${item.score}"

            Glide.with(binding.root)
                .load(item.imageUrl)
                .into(binding.imgAnime)

            binding.root.setOnClickListener {
                onItemClick(item)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimeViewHolder {
        val binding = ItemAnimeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AnimeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AnimeViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<AnimeUiModel>() {
            override fun areItemsTheSame(oldItem: AnimeUiModel, newItem: AnimeUiModel): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: AnimeUiModel, newItem: AnimeUiModel): Boolean {
                return oldItem == newItem
            }
        }
    }
}

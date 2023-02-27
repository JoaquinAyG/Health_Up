package study.project.holders

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import study.project.adapters.ExerciseAdapter
import study.project.databinding.RailItemBinding
import study.project.models.Exercise

class RailHolder(
    val binding: RailItemBinding,
    private val onFavourite: (Exercise) -> Unit,
    private val onClick: (Exercise) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    init {
        binding.rvExercises.layoutManager =
            LinearLayoutManager(binding.root.context, LinearLayoutManager.HORIZONTAL, false)
    }

    fun bind(itemList: List<Exercise>, category: String) {
        binding.rvExercises.adapter = ExerciseAdapter(
            itemList,
            onFavourite,
            onClick
        )
        binding.tvCategoryTitle.text = category
    }
}
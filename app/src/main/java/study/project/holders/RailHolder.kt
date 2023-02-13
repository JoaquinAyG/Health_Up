package study.project.holders

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import study.project.adapters.ExerciseAdapter
import study.project.databinding.RailItemBinding
import study.project.models.Exercise

class RailHolder(
    val binding: RailItemBinding
): RecyclerView.ViewHolder(binding.root) {

    init {
        binding.rvExercises.layoutManager = LinearLayoutManager(binding.root.context, LinearLayoutManager.HORIZONTAL, false)
    }

    fun bind(itemList: List<Exercise>, category: String) {
        binding.rvExercises.adapter = ExerciseAdapter(
            itemList
        )
        binding.tvCategoryTitle.text = category
    }
}
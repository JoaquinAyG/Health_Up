package study.project.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import study.project.databinding.ExerciseItemBinding
import study.project.holders.ExerciseViewHolder
import study.project.models.Exercise

class ExerciseAdapter(
    private val dataList: List<Exercise>,
    private val onFavourite: (Exercise) -> Unit,
    private val onClick: (Exercise) -> Unit,
) : RecyclerView.Adapter<ExerciseViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExerciseViewHolder {
        return ExerciseViewHolder(
            ExerciseItemBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            onFavourite,
            onClick
        )
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: ExerciseViewHolder, position: Int) {
        holder.bind(dataList[position])
    }
}
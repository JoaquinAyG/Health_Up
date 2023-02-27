package study.project.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import study.project.databinding.ExerciseItemBinding
import study.project.holders.FavouriteViewHolder
import study.project.models.Exercise

class FavouritesAdapter(
    private val exerciseList: List<Exercise>,
    val onClick: (Exercise) -> Unit
) : RecyclerView.Adapter<FavouriteViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavouriteViewHolder {
        return FavouriteViewHolder(
            ExerciseItemBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            onClick
        )
    }

    override fun getItemCount(): Int {
        return exerciseList.size
    }

    override fun onBindViewHolder(holder: FavouriteViewHolder, position: Int) {
        holder.bind(exerciseList[position])
    }

}

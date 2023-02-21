package study.project.holders

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import study.project.R
import study.project.databinding.ExerciseItemBinding
import study.project.models.Exercise

class FavouriteViewHolder(
    private val binding: ExerciseItemBinding,
    private val onClick: (Exercise) -> Unit,
): RecyclerView.ViewHolder(binding.root) {

    fun bind(exercise: Exercise) {

        binding.apply{

            tvTitle.text = exercise.nameEn.ifEmpty { exercise.name }
            Glide.with(itemView.context)
                .load(exercise.imageUrlMain)
                .placeholder(android.R.drawable.ic_menu_gallery)
                .into(ivImage)

            itemView.setOnClickListener {
                onClick(exercise)
            }

            binding.ivFavorite.setImageResource(R.drawable.ic_favorite)
        }
    }
}
package study.project.holders

import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import study.project.databinding.ExerciseItemBinding
import study.project.models.Exercise

class ExerciseViewHolder(
    private val binding: ExerciseItemBinding
): RecyclerView.ViewHolder(binding.root) {
    fun bind(exercise: Exercise) {

        binding.apply{
            tvTitle.text = exercise.nameEn.ifEmpty { exercise.name }
            ivFavorite.setOnClickListener {
                Toast.makeText(itemView.context, "Not Implemented", Toast.LENGTH_SHORT).show()
            }

            Glide.with(itemView.context)
                .load(exercise.imageUrlMain)
                .placeholder(android.R.drawable.ic_menu_gallery)
                .into(ivImage)
        }
    }
}
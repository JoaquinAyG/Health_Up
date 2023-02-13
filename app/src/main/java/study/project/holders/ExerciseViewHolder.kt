package study.project.holders

import android.util.Log
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import study.project.R
import study.project.databinding.ExerciseItemBinding
import study.project.models.Exercise
import study.project.models.UserProfile

class ExerciseViewHolder(
    private val binding: ExerciseItemBinding,
    private val onFavourite: (Exercise) -> Unit,
    private val onClick: (Exercise) -> Unit,
): RecyclerView.ViewHolder(binding.root) {
    var favourite = false
    fun bind(exercise: Exercise) {

        favourite = UserProfile.instance.favoriteList.contains(exercise.id)

        binding.apply{

            setFav(ivFavorite)

            tvTitle.text = exercise.nameEn.ifEmpty { exercise.name }

            ivFavorite.setOnClickListener {
                onFavourite(exercise)
                favourite = !favourite
                setFav(ivFavorite)
            }

            Glide.with(itemView.context)
                .load(exercise.imageUrlMain)
                .placeholder(android.R.drawable.ic_menu_gallery)
                .into(ivImage)

            itemView.setOnClickListener {
                onClick(exercise)
            }
        }
    }

    private fun setFav(ivFavorite: ImageView){
        Log.i("ExerciseViewHolder", "fav = $favourite")
        ivFavorite.setImageResource(
            if (favourite)
                R.drawable.ic_favorite
            else
                R.drawable.ic_favorite_border
        )
    }
}
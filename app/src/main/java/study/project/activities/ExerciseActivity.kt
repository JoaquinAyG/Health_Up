package study.project.activities

import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import study.project.R
import study.project.databinding.ActivityExerciseBinding
import study.project.models.Exercise


class ExerciseActivity : AppCompatActivity() {
    val exercise = Exercise()
    lateinit var binding: ActivityExerciseBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exercise)
        binding = ActivityExerciseBinding.inflate(layoutInflater)
        loadImage(binding.imageView, exercise.imageUrlMain)
        binding.imageView.setImageURI(Uri.parse(exercise.imageUrlMain))
        binding.tvTittle.text=exercise.nameEn
        binding.tvCategory.text=exercise.category
        exercise.muscles.forEach{
            binding.tvMusclist.text = "${binding.tvMusclist.text} $it \n"
        }
        binding.tvDesc.text = exercise.descriptionEn




    }

    companion object {
        @JvmStatic
        private fun loadImage(view: ImageView, image: String) {
            Glide.with(view.context)
                .load(image)
                .into(view)
        }
    }
}
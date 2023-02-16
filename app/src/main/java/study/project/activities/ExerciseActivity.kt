package study.project.activities


import android.net.Uri
import android.os.Bundle
import android.text.Html
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import study.project.databinding.ActivityExerciseBinding
import study.project.models.Exercise
import study.project.utils.forceDarkMode

class ExerciseActivity : AppCompatActivity() {
    var exercise = Exercise()
    lateinit var binding: ActivityExerciseBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        forceDarkMode()
        binding = ActivityExerciseBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        exercise = intent.getSerializableExtra("exercise") as Exercise
        loadImage(binding.imageView, exercise.imageUrlMain)
        binding.imageView.setImageURI(Uri.parse(exercise.imageUrlMain))
        binding.tvTittle.text=exercise.nameEn
        binding.tvCategory.text=exercise.category
        exercise.muscles.forEach{
            binding.tvMusclist.text = "${binding.tvMusclist.text} $it \n"
        }
        binding.tvDesc.text = Html.fromHtml(exercise.descriptionEn)

    }

    companion object {
        @JvmStatic
        private fun loadImage(view: ImageView, image: String) {
            Glide.with(view.context)
                .load(image)
                .centerCrop()
                .into(view)
        }

    }
}
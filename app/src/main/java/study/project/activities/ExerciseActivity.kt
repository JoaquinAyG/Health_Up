package study.project.activities


import android.os.Bundle
import android.text.Html
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
        val img = if (exercise.imageUrlMain.isEmpty())
            exercise.imageUrlSecondary
        else
            exercise.imageUrlMain

        Glide.with(binding.root.context)
            .load(img)
            .fitCenter()
            .into(binding.imageView)
        binding.tvTittle.text = exercise.nameEn.ifEmpty { exercise.name }
        binding.tvCategory.text = exercise.category
        exercise.muscles.forEach {
            binding.tvMusclist.text = "${binding.tvMusclist.text} $it \n"
        }
        binding.tvDesc.text = Html.fromHtml(exercise.descriptionEn)
    }
}
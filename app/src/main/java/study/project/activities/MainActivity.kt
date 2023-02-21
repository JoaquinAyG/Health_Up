package study.project.activities
import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.os.Process
import android.view.KeyEvent
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import study.project.HealthUpApplication
import study.project.R
import study.project.databinding.ActivityMainBinding
import study.project.factories.FavViewModelFactory
import study.project.models.Exercise
import study.project.models.Fav
import study.project.models.UserProfile
import study.project.utils.forceDarkMode
import study.project.viewmodels.ExerciseViewModel
import study.project.viewmodels.FavViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: ExerciseViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        forceDarkMode()
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_activity_bottom)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home,
                R.id.navigation_dashboard,
                R.id.navigation_notifications,
                R.id.navigation_profile,
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            AlertDialog.Builder(this)
                .setIcon(R.drawable.logo)
                .setTitle("Are you sure you want to exit?")
                .setCancelable(false)
                .setNegativeButton("Cancel", null)
                .setPositiveButton(
                    "exit"
                ) { _, _ ->
                    Process.killProcess(Process.myPid())
                }.show()
            return true
        }
        return super.onKeyDown(keyCode, event)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.settings_menu, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle item selection
        return when (item.itemId) {
            R.id.settings_menu -> {
                val intent = Intent(this@MainActivity, SettingsActivity ::class.java)
                startActivity(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    fun navigateToExercise(exercise: Exercise) {
        Intent(this, ExerciseActivity::class.java).apply {
            putExtra("exercise", exercise)
            startActivity(this)
        }
    }

    fun navigateToExercise(id: Int) {
        viewModel.exerciseList.value?.let { exercises ->
            val exercise = exercises.find { it.id == id }
            exercise?.let { navigateToExercise(it) }
        }
    }
}
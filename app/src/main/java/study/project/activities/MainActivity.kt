package study.project.activities

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.os.Process
import android.view.KeyEvent
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import study.project.R
import study.project.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
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
    private fun cerrarAplicacion() {
        AlertDialog.Builder(this)
            .setIcon(R.drawable.logo)
            .setTitle("Are you sure you want to exit?")
            .setCancelable(false)
            .setNegativeButton("Cancel", null)
            .setPositiveButton(
                "exit",
                DialogInterface.OnClickListener { dialog, which ->


                    Process.killProcess(Process.myPid())
                }).show()

    }
    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            cerrarAplicacion()
            return true
        }
        return super.onKeyDown(keyCode, event)
    }


}
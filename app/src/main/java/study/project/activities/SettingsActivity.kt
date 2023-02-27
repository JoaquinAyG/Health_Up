package study.project.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import study.project.R
import study.project.databinding.SettingsActivityBinding
import study.project.fragments.SettingsFragment

class SettingsActivity : AppCompatActivity() {

    lateinit var binding: SettingsActivityBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = SettingsActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.settings, SettingsFragment())
                .commit()
        }
    }

    fun logOut() {
        AlertDialog.Builder(this)
            .setIcon(R.drawable.logo)
            .setTitle("Are you sure you want to log out?")
            .setCancelable(false)
            .setNegativeButton("Cancel", null)
            .setPositiveButton(
                "log out"
            ) { _, _ ->
                val intent = Intent(this@SettingsActivity, LoginActivity::class.java)
                startActivity(intent)
            }.show()
    }
}
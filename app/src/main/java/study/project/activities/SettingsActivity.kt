package study.project.activities

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.PreferenceFragmentCompat
import study.project.R
import study.project.databinding.SettingsActivityBinding

class SettingsActivity : AppCompatActivity() {

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
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.btnLogout.setOnClickListener {
            AlertDialog.Builder(this)
                .setIcon(R.drawable.logo)
                .setTitle("Are you sure you want to log out?")
                .setCancelable(false)
                .setNegativeButton("Cancel", null)
                .setPositiveButton(
                    "log out"
                ) { _, _ ->
                    val intent = Intent(this@SettingsActivity, LoginActivity ::class.java)
                    startActivity(intent)
                }.show()
        }

    }

    class SettingsFragment : PreferenceFragmentCompat() {
        override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
            setPreferencesFromResource(R.xml.root_preferences, rootKey)
        }
    }
}
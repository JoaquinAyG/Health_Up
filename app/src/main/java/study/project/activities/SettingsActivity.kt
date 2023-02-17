package study.project.activities

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.os.Process
import android.view.KeyEvent
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.PreferenceFragmentCompat
import study.project.R

class SettingsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.settings_activity)
        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.settings, SettingsFragment())
                .commit()
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    class SettingsFragment : PreferenceFragmentCompat() {
        override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
            setPreferencesFromResource(R.xml.root_preferences, rootKey)
        }
    }
    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
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
            return true
        }
        return super.onKeyDown(keyCode, event)
    }
}
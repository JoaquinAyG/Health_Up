package study.project.activities

import android.R
import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.os.Process
import android.view.KeyEvent
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import study.project.databinding.ActivityLoginBinding
import study.project.utils.Utils


class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide();

        binding.apply {
            btnLogin.setOnClickListener {
                val username = etUsername.text.toString()
                val password = etPassword.text.toString()
                if (Utils.validateCredentials(username, password)) {
                    val intent = Intent(this@LoginActivity, TabbedActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    Toast.makeText(
                        this@LoginActivity,
                        "Invalid username or password",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            tvSignup.setOnClickListener {
                Toast.makeText(this@LoginActivity, "Sign Up", Toast.LENGTH_SHORT).show()
//            val intent = Intent(this, SignupActivity::class.java)
//            startActivity(intent)
            }
        }

    }
    fun ExercisePage(view: View?) {
        val intent = Intent(this, ExerciseActivity::class.java)
        startActivity(intent)
    }
    fun ExercisePage2(view: View?) {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    private fun cerrarAplicacion() {
        AlertDialog.Builder(this)
            .setIcon(study.project.R.drawable.logo)
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
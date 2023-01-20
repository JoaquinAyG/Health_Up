package study.project.activities

import android.content.Intent
import android.os.Bundle
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
                    val intent = Intent(this@LoginActivity, MainActivity::class.java)
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
}
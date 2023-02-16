package study.project.activities

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.os.Process
import android.view.KeyEvent
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import study.project.HealthUpApplication
import study.project.databinding.ActivityLoginBinding
import study.project.factories.UserViewModelFactory
import study.project.models.User
import study.project.utils.forceDarkMode
import study.project.viewmodels.UserViewModel


class LoginActivity : AppCompatActivity() {

    private val userViewModel: UserViewModel by viewModels {
        UserViewModelFactory((application as HealthUpApplication).userRepository)
    }

    private val users = mutableListOf<User>()

    override fun onCreate(savedInstanceState: Bundle?) {
        forceDarkMode()
        super.onCreate(savedInstanceState)

        val binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        observeUsers()

        binding.apply {
            btnLogin.setOnClickListener {
                val username = etUsername.text.toString()
                val password = etPassword.text.toString()
                if (validateCredentials(username, password)) {
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
                val intent = Intent(this@LoginActivity, RegisterActivity::class.java)
                startActivity(intent)
            }
        }

    }

    private fun observeUsers() {
        userViewModel.allUsers.observe(this) {
            it.forEach{ user ->
                users.add(user)
            }
        }
    }

    fun ExercisePage(view: View) {
        Toast.makeText(
            this@LoginActivity,
            "Exercise Page",
            Toast.LENGTH_SHORT
        ).show()
        val intent = Intent(this, ExerciseActivity::class.java)
        startActivity(intent)
    }
    fun ExercisePage2(view: View) {
        Toast.makeText(
            this@LoginActivity,
            "Easter Egg Page",
            Toast.LENGTH_SHORT
        ).show()
        val intent = Intent(this, TabbedActivity::class.java)
        startActivity(intent)
    }

    private fun cerrarAplicacion() {
        AlertDialog.Builder(this)
            .setIcon(study.project.R.drawable.logo)
            .setTitle("Are you sure you want to exit?")
            .setCancelable(false)
            .setNegativeButton("Cancel", null)
            .setPositiveButton(
                "exit"
            ) { _, _ ->
                Process.killProcess(Process.myPid())
            }.show()

    }
    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            cerrarAplicacion()
            return true
        }
        return super.onKeyDown(keyCode, event)
    }

    private fun validateCredentials(username: String, password: String): Boolean {
        var validate = false
        users.forEach {
            validate = when {
                (it.username == username && it.password == password) -> true
                (it.email == username && it.password == password) -> true
                else -> false
            }
        }
        return validate
    }

}
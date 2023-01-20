package study.project.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import study.project.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.registerToolbar.progress = 2

    }
}